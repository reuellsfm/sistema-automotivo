package com.unifecaf.sistemaautomotivo.dto;

import com.unifecaf.sistemaautomotivo.entity.StatusVeiculo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull
    private StatusVeiculo status;
}
