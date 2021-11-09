package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ItensNFCompositeKeyDTO;
import br.com.rd.mvpskins.model.dto.ItensNFDTO;
import br.com.rd.mvpskins.model.embeddable.ItensNFCompositeKey;
import br.com.rd.mvpskins.model.entity.ItensNF;
import br.com.rd.mvpskins.model.entity.Produto;
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

    @Autowired
    PrecoService precoService;

    //  ---------------------> CONVERTER PARA BUSINESS
    private ItensNF dtoToBusiness(ItensNFDTO dto) {
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
    private ItensNFDTO businessToDTO(ItensNF b) {
        ItensNFCompositeKeyDTO id = new ItensNFCompositeKeyDTO();
        id.setProduto(produtoService.getProductById(b.getId().getProduto().getId()));
        id.setNf(nfService.searchInvoiceByID(b.getId().getNf().getId()));

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
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public ItensNFDTO create (ItensNFDTO itemsNFDTO) {
        ItensNF itemsNF = dtoToBusiness(itemsNFDTO);

        //Método que retorna apenas o preço do produto
        Long idProduto = itemsNF.getId().getProduto().getId();
        Double valorProduto = precoService.getLastPrice(idProduto, 1l);
        itemsNF.setValorBruto(valorProduto);

        if(itemsNF.getDesconto() == null){
            itemsNF.setDesconto(0.0);
        }

        Double valorBruto = itemsNF.getValorBruto();
        //Cálculo dos impostos
        Double icms = valorBruto*0.18;
        Double pis = valorBruto*0.065;
        Double cofins = valorBruto*0.03;
        Double ipi = valorBruto*0.1;
        Double desconto = itemsNF.getDesconto();

        //Impostos não são descontados do valor bruto ao consumidor final
        Double valorLiquido = valorBruto-desconto;

        itemsNF.setIcms(icms);
        itemsNF.setIpi(ipi);
        itemsNF.setPis(pis);
        itemsNF.setCofins(cofins);
        itemsNF.setValorBruto(valorBruto);
        itemsNF.setValorLiquido(valorLiquido);
        itemsNF.setQuantidade(1);
        itemsNF = itensNFRepository.save(itemsNF);

        return businessToDTO(itemsNF);
    }


    //  ---------------------> BUSCAR
    //TODOS OS ITENS NF
    public List<ItensNFDTO> searchAll() {
        List<ItensNF> list = itensNFRepository.findAll();

        return listToDTO(list);
    }

    //UM ITEM NF POR ID
    public ItensNFDTO searchID(Long idProduct, Long idNF) {

        ItensNFCompositeKey id = new ItensNFCompositeKey();
        id.setProduto(produtoRepository.getById(idProduct));
        id.setNf(nfRepository.getById(idNF));

        if (itensNFRepository.existsById(id)) {
            return businessToDTO(itensNFRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public ItensNFDTO update(ItensNFDTO dto, Long idProduct, Long idNF) {

        ItensNFCompositeKeyDTO id = new ItensNFCompositeKeyDTO();
        id.setProduto(produtoService.getProductById(idProduct));
        id.setNf(nfService.searchInvoiceByID(idNF));
        dto.setId(id);

        ItensNF itemsNF = dtoToBusiness(dto);
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
            return businessToDTO(update);
        }
//        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long idProduct, Long idNF) {
        ItensNFCompositeKey id = new ItensNFCompositeKey();
        id.setProduto(produtoRepository.getById(idProduct));
        id.setNf(nfRepository.getById(idNF));

        if (itensNFRepository.existsById(id)) {
            itensNFRepository.deleteById(id);
        }
    }
}