package com.unifecaf.sistemaautomotivo.controller;

import com.unifecaf.sistemaautomotivo.dto.MarcaRequest;
import com.unifecaf.sistemaautomotivo.entity.Marca;
import com.unifecaf.sistemaautomotivo.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Marca> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Marca buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marca criar(@Valid @RequestBody MarcaRequest req) {
        return service.criar(req.toEntity());
    }

    @PutMapping("/{id}")
    public Marca atualizar(@PathVariable Long id, @Valid @RequestBody MarcaRequest req) {
        return service.atualizar(id, req.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
