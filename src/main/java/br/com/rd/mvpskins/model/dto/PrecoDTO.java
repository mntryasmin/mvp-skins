package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.embeddable.CompositeKeyPreco;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrecoDTO {

    private CompositeKeyPrecoDTO chaveComposta;

    private Double vlPreco;
}
