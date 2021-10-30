package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.entity.NF;
import lombok.Data;
import java.io.Serializable;

@Data
public class ItensNFCompositeKeyDTO implements Serializable {
    private Long idProduto;
    private NF nf;}
