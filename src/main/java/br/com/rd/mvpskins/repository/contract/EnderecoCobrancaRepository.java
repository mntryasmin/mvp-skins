package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.EnderecoCobranca;
import br.com.rd.mvpskins.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoCobrancaRepository extends JpaRepository<EnderecoCobranca, Long> {

}
