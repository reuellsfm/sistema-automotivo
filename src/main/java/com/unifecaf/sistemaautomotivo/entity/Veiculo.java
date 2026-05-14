package com.unifecaf.sistemaautomotivo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "veiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O modelo e obrigatorio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

    @NotNull(message = "O ano e obrigatorio")
    @Min(value = 1950, message = "Ano minimo: 1950")
    @Max(value = 2100, message = "Ano maximo: 2100")
    @Column(nullable = false)
    private Integer ano;

    @NotBlank(message = "A cor e obrigatoria")
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String cor;

    @NotNull(message = "O preco e obrigatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preco deve ser positivo")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @NotNull(message = "A quilometragem e obrigatoria")
    @Min(value = 0, message = "Quilometragem nao pode ser negativa")
    @Column(nullable = false)
    private Integer quilometragem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;

    public void atualizarStatus(StatusVeiculo novoStatus) {
        this.status = novoStatus;
    }

    public void aplicarDesconto(BigDecimal percentual) {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) <= 0) return;
        BigDecimal fator = BigDecimal.ONE.subtract(percentual.divide(BigDecimal.valueOf(100)));
        this.preco = this.preco.multiply(fator);
    }
}
