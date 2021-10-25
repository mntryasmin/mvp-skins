package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.Raridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaridadeRepository extends JpaRepository<Raridade, Long> {
}
