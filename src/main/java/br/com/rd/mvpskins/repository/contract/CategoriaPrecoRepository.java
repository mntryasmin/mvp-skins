package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.CategoriaPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaPrecoRepository extends JpaRepository<CategoriaPreco, Long> {
}
