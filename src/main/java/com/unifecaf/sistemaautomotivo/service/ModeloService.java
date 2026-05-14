package com.unifecaf.sistemaautomotivo.service;

import com.unifecaf.sistemaautomotivo.entity.Marca;
import com.unifecaf.sistemaautomotivo.entity.Modelo;
import com.unifecaf.sistemaautomotivo.exception.ResourceNotFoundException;
import com.unifecaf.sistemaautomotivo.repository.ModeloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository repository;
    private final MarcaService marcaService;

    public ModeloService(ModeloRepository repository, MarcaService marcaService) {
        this.repository = repository;
        this.marcaService = marcaService;
    }

    public List<Modelo> listar() {
        return repository.findAll();
    }

    public List<Modelo> listarPorMarca(Long marcaId) {
        marcaService.buscarPorId(marcaId);
        return repository.findByMarcaId(marcaId);
    }

    public Modelo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modelo nao encontrado (id=" + id + ")"));
    }

    public Modelo criar(Modelo modelo, Long marcaId) {
        Marca marca = marcaService.buscarPorId(marcaId);
        modelo.setMarca(marca);
        return repository.save(modelo);
    }

    public Modelo atualizar(Long id, Modelo dados, Long marcaId) {
        Modelo atual = buscarPorId(id);
        atual.setNome(dados.getNome());
        atual.setCategoria(dados.getCategoria());
        if (marcaId != null) {
            atual.setMarca(marcaService.buscarPorId(marcaId));
        }
        return repository.save(atual);
    }

    public void deletar(Long id) {
        Modelo atual = buscarPorId(id);
        repository.delete(atual);
    }
}
