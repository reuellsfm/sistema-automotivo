package com.unifecaf.sistemaautomotivo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marca")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da marca e obrigatorio")
    @Size(max = 80)
    @Column(nullable = false, length = 80, unique = true)
    private String nome;

    @Size(max = 50)
    @Column(name = "pais_origem", length = 50)
    private String paisOrigem;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Modelo> modelos = new ArrayList<>();
}
