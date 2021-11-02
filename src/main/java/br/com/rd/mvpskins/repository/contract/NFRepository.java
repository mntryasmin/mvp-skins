package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.NF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NFRepository extends JpaRepository<NF, Long> {
    List<NF> findByClienteCodigoCliente(Long id);
}
