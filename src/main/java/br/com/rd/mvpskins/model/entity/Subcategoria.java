package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "tb_subcategoria_produto")
@Data
public class Subcategoria {

    @EmbeddedId
    private CompositeKeySubcategoria chaveComposta;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;
}
