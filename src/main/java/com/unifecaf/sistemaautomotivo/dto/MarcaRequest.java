package com.unifecaf.sistemaautomotivo.dto;

import com.unifecaf.sistemaautomotivo.entity.Marca;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MarcaRequest {

    @NotBlank
    @Size(max = 80)
    private String nome;

    @Size(max = 50)
    private String paisOrigem;

    public Marca toEntity() {
        Marca m = new Marca();
        m.setNome(this.nome);
        m.setPaisOrigem(this.paisOrigem);
        return m;
    }
}
