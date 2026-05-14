package com.unifecaf.sistemaautomotivo.dto;

import com.unifecaf.sistemaautomotivo.entity.Categoria;
import com.unifecaf.sistemaautomotivo.entity.Modelo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModeloRequest {

    @NotBlank
    @Size(max = 80)
    private String nome;

    private Categoria categoria;

    @NotNull(message = "O ID da marca e obrigatorio")
    private Long marcaId;

    public Modelo toEntity() {
        Modelo m = new Modelo();
        m.setNome(this.nome);
        m.setCategoria(this.categoria);
        return m;
    }
}
