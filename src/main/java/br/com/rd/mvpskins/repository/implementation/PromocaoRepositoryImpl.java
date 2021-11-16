package br.com.rd.mvpskins.repository.implementation;

import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import br.com.rd.mvpskins.repository.contract.PromocaoRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class PromocaoRepositoryImpl implements PromocaoRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Promocao validateCodCoupon (String cod) {
        Query sql = entityManager.createNativeQuery("SELECT * FROM TB_PROMOCAO TP WHERE TP.CUPOM_DESCONTO = ? ORDER BY TP.DATA_FIM DESC", Promocao.class);

        List<Promocao> list = sql.setParameter(1, cod).getResultList();

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public PromocaoProduto validateDateCoupon (Long codPromocao) {
        Query sql = entityManager.createNativeQuery("SELECT * FROM TB_PROMOCAO_PRODUTO TPP WHERE TPP.CODIGO_PROMOCAO = ?", PromocaoProduto.class);

        List<PromocaoProduto> list = sql.setParameter(1, codPromocao).getResultList();

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
