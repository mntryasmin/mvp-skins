package br.com.rd.ProjetoIntegrador.Repository;

import br.com.rd.ProjetoIntegrador.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
