package com.unifecaf.sistemaautomotivo.repository;

import com.unifecaf.sistemaautomotivo.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNomeIgnoreCase(String nome);
}
