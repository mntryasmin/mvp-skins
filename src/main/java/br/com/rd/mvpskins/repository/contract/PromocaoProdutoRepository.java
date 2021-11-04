package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.PromocaoProdutoCompositeKey;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocaoProdutoRepository extends JpaRepository<PromocaoProduto, PromocaoProdutoCompositeKey> {

}
