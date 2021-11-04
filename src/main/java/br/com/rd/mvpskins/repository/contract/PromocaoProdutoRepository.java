package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoProdutoRepository extends JpaRepository<PromocaoProduto,Long> {
}
