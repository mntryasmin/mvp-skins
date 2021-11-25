package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.PromocaoDTO;
import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import br.com.rd.mvpskins.repository.contract.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PromocaoService {
    @Autowired
    PromocaoRepository promocaoRepository;

    private Promocao dtoToBusiness(PromocaoDTO dto) {
        Promocao p = new Promocao();
        p.setDescricao(dto.getDescricao());
        p.setCodigoPromocao(dto.getCodigoPromocao());
        p.setCupomDesconto(dto.getCupomDesconto());
        p.setDataFim(dto.getDataFim());
        p.setPorcentagemDesconto(dto.getPorcentagemDesconto());
        p.setValorDesconto(dto.getValorDesconto());
        return p;
    }

    private PromocaoDTO businessToDto(Promocao p) {
        PromocaoDTO dto = new PromocaoDTO();
        dto.setDescricao(p.getDescricao());
        dto.setCodigoPromocao(p.getCodigoPromocao());
        dto.setCupomDesconto(p.getCupomDesconto());
        dto.setDataFim(p.getDataFim());
        dto.setPorcentagemDesconto(p.getPorcentagemDesconto());
        dto.setValorDesconto(p.getValorDesconto());
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

    public PromocaoDTO searchPromotionById(Long codigoPromocao) {
        if (promocaoRepository.existsById(codigoPromocao)) {
            return businessToDto(promocaoRepository.getById(codigoPromocao));
        }
        return null;
    }

    // Validação de promoção por cupom
    public Boolean validateCouponByCod(String cod) {
        LocalDate today = LocalDate.now();

        try {
            Promocao codExists = promocaoRepository.validateCodCoupon(cod);
            if (codExists != null) {
                try {
                    PromocaoProduto validDate = promocaoRepository.validateDateCoupon(codExists.getCodigoPromocao());
                    if (codExists.getDataFim() != null) {
                            if (validDate.getId().getDataInicio().compareTo(today) <= 0 & codExists.getDataFim().compareTo(today) >= 0) {
                            return true;
                        }
                    } else {
                        if (validDate.getId().getDataInicio().compareTo(today) <= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    System.out.print("Ocorreu um erro na validação da data do cupom: " + e);
                }
            } else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.print("Ocorreu um erro na validação do código do cupom: " + e);
        }

        return null;
    }

    // Busca de desconto por cupom
    public PromocaoDTO searchDiscount(String cod) {
        List<Promocao> promo = promocaoRepository.findBycupomDescontoOrderByDataFimDesc(cod);

        try {
            if (promo != null){
                Promocao p = promo.get(0);
                return this.businessToDto(p);
            }else {
                return null;
            }
        } catch (Exception e) {
            System.out.print("Ocorreu um erro na busca pelo desconto: " + e);
        }

        return null;
    }

    // Update promocao
    public PromocaoDTO updatePromotion(PromocaoDTO dto, Long codigoPromocao) {
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

            if (promocao.getCupomDesconto() != null) {
                update.setCupomDesconto(promocao.getCupomDesconto());
            }

            if (promocao.getDataFim() != null) {
                update.setDataFim(promocao.getDataFim());
            }
            if (promocao.getPorcentagemDesconto() != null) {
                update.setPorcentagemDesconto(promocao.getPorcentagemDesconto());
            }
            if (promocao.getValorDesconto() != null) {
                update.setValorDesconto(promocao.getValorDesconto());
            }

            promocaoRepository.save(update);
        }
        return null;
    }
    public void deletePromotion (Long id){
        if (promocaoRepository.existsById(id)){
            promocaoRepository.deleteById(id);
        }
}
}
