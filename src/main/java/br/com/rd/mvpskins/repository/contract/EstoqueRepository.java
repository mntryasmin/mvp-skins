package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyEstoque;
import br.com.rd.mvpskins.model.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, CompositeKeyEstoque> {

    List<Estoque> findAllByFlagQuantidadeTrue();
    List<Estoque> findAllByFlagQuantidadeFalse();

    @Query(value = "SELECT flag_quantidade FROM tb_estoque WHERE " +
            "codigo_produto = :produto", nativeQuery = true)
    Boolean checkProductInventory(@Param("produto") Long idProduto);

}
