package br.com.rd.mvpskins.repository.contract;


import br.com.rd.mvpskins.model.embeddable.ItensPedidoCompositeKey;
import br.com.rd.mvpskins.model.entity.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, ItensPedidoCompositeKey>, ItensPedidoRepositoryCustom {
    List<ItensPedido> searchProdutosPedido(Long idPedido);

    List<ItensPedido> searchProdutosMaisVendidos();

    List<ItensPedido> searchFacasMaisVendidas();

    List<ItensPedido> searchArmasMaisVendidas();
}
