package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyFavoritos;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "tb_favoritos")
@Data
public class Favoritos {

    @EmbeddedId
    private CompositeKeyFavoritos chaveComposta;

    @Column(name="FLAG_FAVORITO", nullable = false)
    private boolean favorito;
}
