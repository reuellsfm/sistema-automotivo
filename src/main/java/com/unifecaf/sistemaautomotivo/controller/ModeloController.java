package com.unifecaf.sistemaautomotivo.controller;

import com.unifecaf.sistemaautomotivo.dto.ModeloRequest;
import com.unifecaf.sistemaautomotivo.entity.Modelo;
import com.unifecaf.sistemaautomotivo.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
@CrossOrigin(origins = "*")
public class ModeloController {

    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @GetMapping
    public List<Modelo> listar(@RequestParam(required = false) Long marcaId) {
        if (marcaId != null) return service.listarPorMarca(marcaId);
        return service.listar();
    }

    @GetMapping("/{id}")
    public Modelo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Modelo criar(@Valid @RequestBody ModeloRequest req) {
        return service.criar(req.toEntity(), req.getMarcaId());
    }

    @PutMapping("/{id}")
    public Modelo atualizar(@PathVariable Long id, @Valid @RequestBody ModeloRequest req) {
        return service.atualizar(id, req.toEntity(), req.getMarcaId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
