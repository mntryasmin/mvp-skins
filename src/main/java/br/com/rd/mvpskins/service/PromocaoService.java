package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.PromocaoDTO;
import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.repository.contract.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromocaoService {
    @Autowired
    PromocaoRepository promocaoRepository;

    private Promocao dtoToBusiness(PromocaoDTO dto) {
        Promocao p = new Promocao();
        p.setDescricao(dto.getDescricao());
        p.setCodigoPromocao(dto.getCodigoPromocao());
        return p;
    }

    private PromocaoDTO businessToDto(Promocao p) {
        PromocaoDTO dto = new PromocaoDTO();
        dto.setDescricao(p.getDescricao());
        dto.setCodigoPromocao(p.getCodigoPromocao());
        return dto;
    }

    private List<PromocaoDTO> listToDto(List<Promocao> listP) {
        List<PromocaoDTO> listDTO = new ArrayList<>();
        for (Promocao p : listP) {
            listDTO.add(this.businessToDto(p));
        }
        return listDTO;
    }

    // Criar promocao
    public PromocaoDTO createPromotion(PromocaoDTO promocaoDTO) {
        Promocao promocao = dtoToBusiness(promocaoDTO);
        promocao = promocaoRepository.save(promocao);
        return businessToDto(promocao);
    }

    // Buscar promocao
    public List<PromocaoDTO> searchAllPromotion() {
        List<Promocao> lista = promocaoRepository.findAll();
        return listToDto(lista);
    }

    public PromocaoDTO searchPromotionById(String codigoPromocao) {
        if (promocaoRepository.existsById(codigoPromocao)) {
            return businessToDto(promocaoRepository.getById(codigoPromocao));
        }
        return null;
    }

    // Update promocao
    public PromocaoDTO updatePromotion(PromocaoDTO dto, String codigoPromocao) {
        Optional<Promocao> opt = promocaoRepository.findById(codigoPromocao);
        Promocao promocao = dtoToBusiness(dto);
        if (opt.isPresent()) {
            Promocao update = opt.get();
            if (promocao.getCodigoPromocao() != null) {
                update.setCodigoPromocao(promocao.getCodigoPromocao());
            }
            if (promocao.getDescricao() != null) {
                update.setDescricao(promocao.getDescricao());
            }
            promocaoRepository.save(update);
        }
        return null;
    }
    public void deletePromotion (String id){
        if (promocaoRepository.existsById(id)){
            promocaoRepository.deleteById(id);
        }
}





}
