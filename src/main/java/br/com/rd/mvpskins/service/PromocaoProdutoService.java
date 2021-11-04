package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.PromocaoProdutoCompositeKeyDTO;
import br.com.rd.mvpskins.model.dto.PromocaoProdutoDTO;
import br.com.rd.mvpskins.model.embeddable.PromocaoProdutoCompositeKey;
import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import br.com.rd.mvpskins.repository.contract.PromocaoProdutoRepository;
import br.com.rd.mvpskins.repository.contract.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromocaoProdutoService {
    @Autowired
    PromocaoProdutoRepository promocaoProdutoRepository;
    @Autowired
    PromocaoProdutoService promocaoProdutoService;
    @Autowired
    Promocao promocaoRepository;
    @Autowired
    PromocaoService promocaoService;

    private PromocaoProduto dtoToBusiness(PromocaoProdutoDTO dto) {
        PromocaoProdutoCompositeKey id = new PromocaoProdutoCompositeKey();
        id.setPromocao(dto.getId().getPromocaoDTO());
        id.setPromocao(dto.getId().getPromocaoDTO());
        id.setDataInicio(dto.getId().getDataInicio());

        PromocaoProduto p = new PromocaoProduto();
        p.setCodigoProduto(dto.getCodigoProduto());
        p.setDataFim(dto.getDataFim());
        p.setPorcentagemDesconto(dto.getPorcentagemDesconto());
        p.setValorDesconto(dto.getValorDesconto());


        return p;
    }


    private PromocaoProdutoDTO businessToDto(PromocaoProduto p) {
    PromocaoProdutoCompositeKeyDTO id = new PromocaoProdutoCompositeKeyDTO();
    id.setPromocaoProdutoDTO(p.getId().getPromocaoProduto());
    id.setPromocaoDTO(p.getId().getPromocao());
    id.setDataInicio(p.getId().getDataInicio());

        PromocaoProdutoDTO dto = new PromocaoProdutoDTO();

        dto.setCodigoProduto(p.getCodigoProduto());
        dto.setDataFim(p.getDataFim());
        dto.setPorcentagemDesconto(p.getPorcentagemDesconto());
        dto.setValorDesconto(p.getValorDesconto());


        return dto;

    }

    private List<PromocaoProdutoDTO> listToDTO(List<PromocaoProduto> listP) {
        List<PromocaoProdutoDTO> listDTO = new ArrayList<>();

        for (PromocaoProduto p : listP) {
            listDTO.add(this.businessToDto(p));
        }
        return listDTO;
    }

    public PromocaoProdutoDTO createPromotion(PromocaoProdutoDTO promocaoProdutoDTO) {
        PromocaoProduto promocaoProduto = dtoToBusiness(promocaoProdutoDTO);
        promocaoProduto = promocaoProdutoRepository.save(promocaoProduto);
        return businessToDto(promocaoProduto);
    }

    public List<PromocaoProdutoDTO> searchAll() {
        List<PromocaoProduto> lista = promocaoProdutoRepository.findAll();
        return listToDTO(lista);
    }

    public PromocaoProdutoDTO searchPromotionById(Long codigoProduto) {
        if (promocaoProdutoRepository.existsById(codigoProduto)) {
            return businessToDto(promocaoProdutoRepository.getById(codigoProduto));
        }
        return null;
    }

    public PromocaoProdutoDTO updatePromotion(PromocaoProdutoDTO dto, Long codigoProduto) {
        Optional<PromocaoProduto> opt = promocaoProdutoRepository.findById(codigoProduto);
        PromocaoProduto promocaoProduto = dtoToBusiness(dto);
        if (opt.isPresent()) {
            PromocaoProduto update = opt.get();
            if (promocaoProduto.getCodigoProduto() != null) {
                update.setCodigoProduto(promocaoProduto.getCodigoProduto());
            }

            if (promocaoProduto.getDataFim() != null) {
                update.setDataFim(promocaoProduto.getDataFim());
            }
            if (promocaoProduto.getPorcentagemDesconto() != null) {
                update.setPorcentagemDesconto(promocaoProduto.getPorcentagemDesconto());
            }
            if (promocaoProduto.getValorDesconto() != null) {
                update.setValorDesconto(promocaoProduto.getValorDesconto());
            }

            promocaoProdutoRepository.save(update);
            return businessToDto(update);
        }
        return null;
    }

        public void deleteproductPromotion(Long id){
        if (promocaoProdutoRepository.existsById(id)){
            promocaoProdutoRepository.deleteById(id);
        }
        }
}





