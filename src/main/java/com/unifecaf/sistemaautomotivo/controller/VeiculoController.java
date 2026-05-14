package com.unifecaf.sistemaautomotivo.controller;

import com.unifecaf.sistemaautomotivo.dto.StatusUpdateRequest;
import com.unifecaf.sistemaautomotivo.dto.VeiculoRequest;
import com.unifecaf.sistemaautomotivo.entity.StatusVeiculo;
import com.unifecaf.sistemaautomotivo.entity.Veiculo;
import com.unifecaf.sistemaautomotivo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Veiculo> filtrar(
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) Long modeloId,
            @RequestParam(required = false) StatusVeiculo status,
            @RequestParam(required = false) Integer anoMin,
            @RequestParam(required = false) Integer anoMax,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax
    ) {
        return service.filtrar(marcaId, modeloId, status, anoMin, anoMax, precoMin, precoMax);
    }

    @GetMapping("/{id}")
    public Veiculo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/resumo")
    public Map<String, Long> resumo() {
        return service.resumoEstoque();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo criar(@Valid @RequestBody VeiculoRequest req) {
        return service.criar(req.toEntity(), req.getModeloId());
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @Valid @RequestBody VeiculoRequest req) {
        return service.atualizar(id, req.toEntity(), req.getModeloId());
    }

    @PatchMapping("/{id}/status")
    public Veiculo atualizarStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest req) {
        return service.atualizarStatus(id, req.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
