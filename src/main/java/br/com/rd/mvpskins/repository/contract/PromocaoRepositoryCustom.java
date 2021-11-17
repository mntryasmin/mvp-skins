package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoRepositoryCustom {
    Promocao validateCodCoupon (String id);
    PromocaoProduto validateDateCoupon (Long id);
}
