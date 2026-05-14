package com.unifecaf.sistemaautomotivo.service;

import com.unifecaf.sistemaautomotivo.entity.Modelo;
import com.unifecaf.sistemaautomotivo.entity.StatusVeiculo;
import com.unifecaf.sistemaautomotivo.entity.Veiculo;
import com.unifecaf.sistemaautomotivo.exception.ResourceNotFoundException;
import com.unifecaf.sistemaautomotivo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;
    private final ModeloService modeloService;

    public VeiculoService(VeiculoRepository repository, ModeloService modeloService) {
        this.repository = repository;
        this.modeloService = modeloService;
    }

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public List<Veiculo> filtrar(Long marcaId, Long modeloId, StatusVeiculo status,
                                 Integer anoMin, Integer anoMax,
                                 BigDecimal precoMin, BigDecimal precoMax) {
        return repository.filtrar(marcaId, modeloId, status, anoMin, anoMax, precoMin, precoMax);
    }

    public Veiculo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo nao encontrado (id=" + id + ")"));
    }

    public Veiculo criar(Veiculo veiculo, Long modeloId) {
        Modelo modelo = modeloService.buscarPorId(modeloId);
        veiculo.setModelo(modelo);
        if (veiculo.getStatus() == null) veiculo.setStatus(StatusVeiculo.DISPONIVEL);
        return repository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo dados, Long modeloId) {
        Veiculo atual = buscarPorId(id);
        atual.setAno(dados.getAno());
        atual.setCor(dados.getCor());
        atual.setPreco(dados.getPreco());
        atual.setQuilometragem(dados.getQuilometragem());
        if (dados.getStatus() != null) atual.setStatus(dados.getStatus());
        if (modeloId != null) {
            atual.setModelo(modeloService.buscarPorId(modeloId));
        }
        return repository.save(atual);
    }

    public Veiculo atualizarStatus(Long id, StatusVeiculo novoStatus) {
        Veiculo atual = buscarPorId(id);
        atual.atualizarStatus(novoStatus);
        return repository.save(atual);
    }

    public void deletar(Long id) {
        Veiculo atual = buscarPorId(id);
        repository.delete(atual);
    }

    public Map<String, Long> resumoEstoque() {
        return Map.of(
                "total", repository.count(),
                "disponivel", repository.countByStatus(StatusVeiculo.DISPONIVEL),
                "reservado", repository.countByStatus(StatusVeiculo.RESERVADO),
                "vendido", repository.countByStatus(StatusVeiculo.VENDIDO)
        );
    }
}
