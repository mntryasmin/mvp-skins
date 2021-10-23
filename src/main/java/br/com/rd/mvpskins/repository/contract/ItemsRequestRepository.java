package br.com.rd.mvpskins.repository.contract;


import br.com.rd.mvpskins.model.embeddable.ItemsRequestCompositeKey;
import br.com.rd.mvpskins.model.entity.ItemsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRequestRepository extends JpaRepository<ItemsRequest, ItemsRequestCompositeKey> {
}
