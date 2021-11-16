package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
    Promocao validateCodCoupon (String id);
    PromocaoProduto validateDateCoupon(Long id);
    List<Promocao> findBycupomDescontoOrderByDataFimDesc(String coupon);
}
