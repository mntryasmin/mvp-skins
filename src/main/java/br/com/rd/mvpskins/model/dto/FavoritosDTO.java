package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyFavoritos;
import lombok.Data;

@Data
public class FavoritosDTO {

    private CompositeKeyFavoritosDTO chaveComposta;
    private Boolean favorito;
}
