package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ItensNFCompositeKeyDTO;
import br.com.rd.mvpskins.model.dto.ItensNFDTO;
import br.com.rd.mvpskins.model.embeddable.ItensNFCompositeKey;
import br.com.rd.mvpskins.model.entity.ItensNF;
import br.com.rd.mvpskins.repository.contract.ItensNFRepository;
import br.com.rd.mvpskins.repository.contract.NFRepository;
import br.com.rd.mvpskins.repository.contract.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItensNFService {

    @Autowired
    ItensNFRepository itensNFRepository;

    @Autowired
    NFService nfService;

    @Autowired
    NFRepository nfRepository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoRepository produtoRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private ItensNF businessToDTO(ItensNFDTO dto) {
        ItensNFCompositeKey id = new ItensNFCompositeKey();
        id.setProduto(produtoRepository.getById(dto.getId().getProduto().getId()));
        id.setNf(nfRepository.getById(dto.getId().getNf().getId()));

        ItensNF b = new ItensNF();
        b.setId(id);
        b.setQuantidade(dto.getQuantidade());
        b.setIcms(dto.getIcms());
        b.setPis(dto.getPis());
        b.setCofins(dto.getCofins());
        b.setIpi(dto.getIpi());
        b.setDesconto(dto.getDesconto());
        b.setValorBruto(dto.getValorBruto());
        b.setValorLiquido(dto.getValorLiquido());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private ItensNFDTO dtoToBusiness(ItensNF b) {
        ItensNFCompositeKeyDTO id = new ItensNFCompositeKeyDTO();
        id.setProduto(produtoService.getProductById(b.getId().getProduto().getId()));
        id.setNf(nfService.searchID(b.getId().getNf().getId()));

        ItensNFDTO dto = new ItensNFDTO();
        dto.setId(id);
        dto.setQuantidade(b.getQuantidade());
        dto.setIcms(b.getIcms());
        dto.setPis(b.getPis());
        dto.setCofins(b.getCofins());
        dto.setIpi(b.getIpi());
        dto.setDesconto(b.getDesconto());
        dto.setValorBruto(b.getValorBruto());
        dto.setValorLiquido(b.getValorLiquido());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<ItensNFDTO> listToDTO(List<ItensNF> listB) {
        List<ItensNFDTO> listDTO = new ArrayList<>();

        for (ItensNF b : listB) {
            listDTO.add(this.dtoToBusiness(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public ItensNFDTO create (ItensNFDTO itensNFDTO) {
        ItensNF itemsNF = businessToDTO(itensNFDTO);

        itemsNF = itensNFRepository.save(itemsNF);

        return dtoToBusiness(itemsNF);
    }


    //  ---------------------> BUSCAR
    //TODOS OS ITENS NF
    public List<ItensNFDTO> searchAll() {
        List<ItensNF> list = itensNFRepository.findAll();

        return listToDTO(list);
    }

    //UM ITEM NF POR ID
    public ItensNFDTO searchID(Long idProduto, Long idNF) {

        ItensNFCompositeKey id = new ItensNFCompositeKey();
        id.setProduto(produtoRepository.getById(idProduto));
        id.setNf(nfRepository.getById(idNF));

        if (itensNFRepository.existsById(id)) {
            return dtoToBusiness(itensNFRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public ItensNFDTO update(ItensNFDTO dto, Long idProduto, Long idNF) {

            ItensNFCompositeKeyDTO id = new ItensNFCompositeKeyDTO();
            id.setProduto(produtoService.getProductById(idProduto));
            id.setNf(nfService.searchID(idNF));
            dto.setId(id);

            ItensNF itemsNF = businessToDTO(dto);
            Optional<ItensNF> opt = itensNFRepository.findById(itemsNF.getId());

            if (opt.isPresent()) {
                ItensNF update = opt.get();

                if (itemsNF.getQuantidade() != null) {
                    update.setQuantidade(itemsNF.getQuantidade());
                }

                if (itemsNF.getDesconto() != null) {
                    update.setDesconto(itemsNF.getDesconto());
                }

                if (itemsNF.getValorBruto() != null) {
                    update.setValorBruto(itemsNF.getValorBruto());
                }

                if (itemsNF.getValorLiquido() != null) {
                    update.setValorLiquido(itemsNF.getValorLiquido());
                }

                itensNFRepository.save(update);
                return dtoToBusiness(update);
            }
//        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long idProduto, Long idNF) {
        ItensNFCompositeKey id = new ItensNFCompositeKey();
        id.setProduto(produtoRepository.getById(idProduto));
        id.setNf(nfRepository.getById(idNF));

        if (itensNFRepository.existsById(id)) {
            itensNFRepository.deleteById(id);
        }
    }
}