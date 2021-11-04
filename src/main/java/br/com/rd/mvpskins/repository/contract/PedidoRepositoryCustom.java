package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositoryCustom {
    List<Pedido> searchPedidosCliente(Long idCliente);
}