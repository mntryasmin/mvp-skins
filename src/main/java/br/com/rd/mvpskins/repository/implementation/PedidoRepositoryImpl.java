package br.com.rd.mvpskins.repository.implementation;

import br.com.rd.mvpskins.model.entity.ItensNF;
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
    public List<Pedido> searchPedidosCliente(Long idCliente) {

        Query sql = entityManager.createNativeQuery("SELECT * FROM TB_PEDIDO TP WHERE TP.CODIGO_CLIENTE = ?" , Pedido.class);
        sql.setParameter(1, idCliente);

        List<Pedido> list = sql.getResultList();
        return list;
    }
}