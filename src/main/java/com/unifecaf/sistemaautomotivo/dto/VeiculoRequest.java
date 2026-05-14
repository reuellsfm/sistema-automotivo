package com.unifecaf.sistemaautomotivo.dto;

import com.unifecaf.sistemaautomotivo.entity.StatusVeiculo;
import com.unifecaf.sistemaautomotivo.entity.Veiculo;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VeiculoRequest {

    @NotNull(message = "O ID do modelo e obrigatorio")
    private Long modeloId;

    @NotNull
    @Min(1950)
    @Max(2100)
    private Integer ano;

    @NotBlank
    @Size(max = 30)
    private String cor;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal preco;

    @NotNull
    @Min(0)
    private Integer quilometragem;

    private StatusVeiculo status;

    public Veiculo toEntity() {
        Veiculo v = new Veiculo();
        v.setAno(this.ano);
        v.setCor(this.cor);
        v.setPreco(this.preco);
        v.setQuilometragem(this.quilometragem);
        if (this.status != null) v.setStatus(this.status);
        return v;
    }
}
