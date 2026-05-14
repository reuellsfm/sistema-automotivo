package com.unifecaf.sistemaautomotivo.service;

import com.unifecaf.sistemaautomotivo.entity.Marca;
import com.unifecaf.sistemaautomotivo.exception.ResourceNotFoundException;
import com.unifecaf.sistemaautomotivo.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository repository;

    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    public List<Marca> listar() {
        return repository.findAll();
    }

    public Marca buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca nao encontrada (id=" + id + ")"));
    }

    public Marca criar(Marca marca) {
        return repository.save(marca);
    }

    public Marca atualizar(Long id, Marca dados) {
        Marca atual = buscarPorId(id);
        atual.setNome(dados.getNome());
        atual.setPaisOrigem(dados.getPaisOrigem());
        return repository.save(atual);
    }

    public void deletar(Long id) {
        Marca atual = buscarPorId(id);
        repository.delete(atual);
    }
}
