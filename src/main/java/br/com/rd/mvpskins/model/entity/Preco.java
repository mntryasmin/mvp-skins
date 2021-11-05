package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyPreco;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name="tb_preco")
@Data
public class Preco {

    @EmbeddedId
    private CompositeKeyPreco chaveComposta;

    @Column(name="VALOR_PRECO", nullable = false)
    private Double vlPreco;

}
