package com.unifecaf.sistemaautomotivo.repository;

import com.unifecaf.sistemaautomotivo.entity.StatusVeiculo;
import com.unifecaf.sistemaautomotivo.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("""
        SELECT v FROM Veiculo v
        WHERE (:marcaId IS NULL OR v.modelo.marca.id = :marcaId)
          AND (:modeloId IS NULL OR v.modelo.id = :modeloId)
          AND (:status IS NULL OR v.status = :status)
          AND (:anoMin IS NULL OR v.ano >= :anoMin)
          AND (:anoMax IS NULL OR v.ano <= :anoMax)
          AND (:precoMin IS NULL OR v.preco >= :precoMin)
          AND (:precoMax IS NULL OR v.preco <= :precoMax)
        ORDER BY v.id DESC
    """)
    List<Veiculo> filtrar(
            @Param("marcaId") Long marcaId,
            @Param("modeloId") Long modeloId,
            @Param("status") StatusVeiculo status,
            @Param("anoMin") Integer anoMin,
            @Param("anoMax") Integer anoMax,
            @Param("precoMin") BigDecimal precoMin,
            @Param("precoMax") BigDecimal precoMax
    );

    long countByStatus(StatusVeiculo status);
}
