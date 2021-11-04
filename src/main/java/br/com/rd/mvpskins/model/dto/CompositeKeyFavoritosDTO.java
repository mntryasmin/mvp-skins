package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.entity.Cliente;
import br.com.rd.mvpskins.model.entity.Produto;
import lombok.Data;

@Data
public class CompositeKeyFavoritosDTO {

    private ClienteDTO cliente;
    private ProdutoDTO produto;
}
