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
    public List<Pedido> searchProdutosCliente(Long idCliente) {

        Query sql = entityManager.createNativeQuery("SELECT CODIGO_PRODUTO FROM TB_ITENS_NF TIN INNER JOIN TB_NF TN ON TN.CODIGO_CLIENTE = ?" , ItensNF.class);
        sql.setParameter(1, idCliente);

        List<Pedido> list = sql.getResultList();
        return list;
    }
}