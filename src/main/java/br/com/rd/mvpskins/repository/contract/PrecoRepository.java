package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyPreco;
import br.com.rd.mvpskins.model.entity.Preco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, CompositeKeyPreco> {

    //Método para retornar apenas o preço do produto
    @Query(value = "SELECT * FROM tb_preco WHERE codigo_categoria_preco = :categoria " +
            "AND codigo_produto = :produto", nativeQuery = true)
    List<Preco> filtrarValorProduto(@Param("categoria") Long idCategoria, @Param("produto") Long idProduto);
}
