package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.ItensNFCompositeKey;
import br.com.rd.mvpskins.model.entity.ItensNF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensNFRepository extends JpaRepository<ItensNF, ItensNFCompositeKey> {
}
