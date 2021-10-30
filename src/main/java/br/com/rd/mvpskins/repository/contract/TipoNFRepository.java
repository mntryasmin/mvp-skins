package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.TipoNF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoNFRepository extends JpaRepository<TipoNF, Long> {
}
