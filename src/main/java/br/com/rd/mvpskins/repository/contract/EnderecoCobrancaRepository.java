package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.EnderecoCobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoCobrancaRepository extends JpaRepository<EnderecoCobranca, Long> {
    EnderecoCobranca findByPedidoId(Long id);

}
