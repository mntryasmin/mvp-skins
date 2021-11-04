package br.com.rd.mvpskins.repository.implementation;

import br.com.rd.mvpskins.model.entity.ItensPedido;
import br.com.rd.mvpskins.repository.contract.ItensPedidoRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItensPedidoRepositoryImpl implements ItensPedidoRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ItensPedido> searchProdutosPedido (Long idPedido) {
        Query sql = entityManager.createNativeQuery("SELECT CODIGO_PRODUTO FROM TB_ITENS_PEDIDO TIP WHERE TIP.CODIGO_PEDIDO = ?", ItensPedido.class);
        sql.setParameter(1, idPedido);

        List<ItensPedido> list = sql.getResultList();
        return list;
    }

    @Override
    public List<ItensPedido> searchProdutosMaisVendidos() {
        Query sql = entityManager.createNativeQuery("SELECT TIP.CODIGO_PRODUTO FROM TB_ITENS_PEDIDO TIP INNER JOIN TB_PRODUTO TP ON (TIP.CODIGO_PRODUTO = TP.CODIGO_PRODUTO) GROUP BY TP.DESCRICAO LIMIT 12", ItensPedido.class);

        List<ItensPedido> list = sql.getResultList();
        return list;
    }

    @Override
    public List<ItensPedido> searchFacasMaisVendidas() {
        Query sql = entityManager.createNativeQuery("SELECT TIP.CODIGO_PRODUTO FROM TB_ITENS_PEDIDO TIP INNER JOIN TB_PRODUTO TP ON (TIP.CODIGO_PRODUTO = TP.CODIGO_PRODUTO) WHERE TP.CODIGO_CATEGORIA = 6 GROUP BY TP.DESCRICAO LIMIT 12", ItensPedido.class);

        List<ItensPedido> list = sql.getResultList();
        return list;
    }

    @Override
    public List<ItensPedido> searchArmasMaisVendidas() {
        Query sql = entityManager.createNativeQuery("SELECT TIP.CODIGO_PRODUTO FROM TB_ITENS_PEDIDO TIP INNER JOIN TB_PRODUTO TP ON (TIP.CODIGO_PRODUTO = TP.CODIGO_PRODUTO) WHERE TP.CODIGO_CATEGORIA = 6 GROUP BY TP.DESCRICAO LIMIT 12", ItensPedido.class);

        List<ItensPedido> list = sql.getResultList();
        return list;
    }
}
