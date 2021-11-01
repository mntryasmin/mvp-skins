package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value="SELECT * FROM tb_produto tp" +
            " INNER JOIN tb_categoria_produto tcp on tcp.CODIGO_CATEGORIA = tp.CODIGO_CATEGORIA" +
            " WHERE tp.DESCRICAO LIKE %:search%" +
            " OR tcp.DESCRICAO LIKE %:search%", nativeQuery = true)
    List<Produto> getListBySearch(@Param("search") String search);

    @Query(value="SELECT * FROM tb_produto tp" +
            " WHERE tp.CODIGO_CATEGORIA = :idCategory", nativeQuery = true)
    List<Produto> getListByCategory(@Param("idCategory") Long idCategory);

    @Query(value="SELECT * FROM tb_produto tp" +
            " WHERE tp.CODIGO_CATEGORIA = :idCategory" +
            " AND tp.CODIGO_SUBCATEGORIA = :idSubcategory", nativeQuery = true)
    List<Produto> getListBySubcategory(@Param("idCategory")Long idCategory,
                                       @Param("idSubcategory") Long idSubcategory);

    @Query(value="SELECT * FROM tb_produto tp" +
            " WHERE tp.CODIGO_COLECAO = :idColection", nativeQuery = true)
    List<Produto> getListByColection(@Param("idColection")Long idColection);

    @Query(value="SELECT * FROM tb_produto tp" +
            " WHERE tp.CODIGO_RARIDADE = :idRarity", nativeQuery = true)
    List<Produto> getListByRarity(@Param("idRarity") Long idRarity);

    @Query(value="SELECT * FROM tb_produto tp" +
            " WHERE tp.CODIGO_EXTERIOR = :idExterior", nativeQuery = true)
    List<Produto> getListByExterior(@Param("idExterior")Long idExterior);

    @Query(value = "SELECT * FROM tb_produto tp" +
            " INNER JOIN tb_preco p ON p.CODIGO_PRODUTO = tp.CODIGO_PRODUTO" +
            " WHERE p.VALOR_PRECO <= :value", nativeQuery = true)
    List<Produto> getListByMaxValue(@Param("value") Double value);

    @Query(value = "SELECT * FROM tb_produto tp" +
            " INNER JOIN tb_preco p ON p.CODIGO_PRODUTO = tp.CODIGO_PRODUTO" +
            " WHERE p.VALOR_PRECO >= :value", nativeQuery = true)
    List<Produto> getListByMinValue(@Param("value") Double value);
}

