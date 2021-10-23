package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.ItemsNFCompositeKey;
import br.com.rd.mvpskins.model.entity.ItemsNF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsNFRepository extends JpaRepository<ItemsNF, ItemsNFCompositeKey> {
}
