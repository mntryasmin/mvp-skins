package br.com.rd.mvpskins.repository.implementation;

import br.com.rd.mvpskins.model.entity.NF;
import br.com.rd.mvpskins.model.entity.Pedido;
import br.com.rd.mvpskins.repository.contract.PedidoRepositoryCustom;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Pedido> searchPedidoCliente(Long id) {

        Query sql = entityManager.createNativeQuery("SELECT CODIGO_PEDIDO FROM TB_NF TN WHERE TN.CODIGO_CLIENTE = ?" , NF.class);
        sql.setParameter(1, id);

        List<Pedido> list = sql.getResultList();
        return list;
    }
}