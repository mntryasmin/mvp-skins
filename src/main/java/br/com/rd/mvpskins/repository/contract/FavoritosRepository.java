package br.com.rd.mvpskins.repository.contract;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyFavoritos;
import br.com.rd.mvpskins.model.entity.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, CompositeKeyFavoritos> {

    @Query(value="SELECT * FROM tb_produto tp" +
            " INNER JOIN tb_favoritos tf ON tp.CODIGO_PRODUTO = tf.CODIGO_PRODUTO" +
            " WHERE tf.CODIGO_CLIENTE = :idC" +
            " AND tf.FLAG_FAVORITO = TRUE", nativeQuery = true)
    List<Favoritos> getListByClientId(@Param("idC")Long id);
}
