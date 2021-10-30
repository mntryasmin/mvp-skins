package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyEstoque;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "TB_ESTOQUE")
@Data
public class Estoque {

    @EmbeddedId
    private CompositeKeyEstoque compositeKeyEstoque;
    @Column(name = "FLAG_QUANTIDADE")
    private Boolean flagQuantidade;
}
