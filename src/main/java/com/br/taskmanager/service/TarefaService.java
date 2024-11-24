package com.br.taskmanager.service;

import com.br.taskmanager.entity.Tarefa;
import com.br.taskmanager.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa cadastrar(Tarefa tarefa) {
        tarefa.setUsuario(null);
        tarefa.setPrazoConclusao(LocalDate.now());
        tarefa.setStatus(true);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> buscarTodos() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> alterar(Tarefa tarefa) {
        Optional<Tarefa> tarefaAtual = tarefaRepository.findById(tarefa.getId());
        if (tarefaAtual.isPresent()) {
            Tarefa tarefaParaAtualizar = tarefaAtual.get();
            tarefaParaAtualizar.setTitulo(tarefa.getTitulo());
            tarefaParaAtualizar.setDescricao(tarefa.getDescricao());
            tarefaParaAtualizar.setPrazoConclusao(tarefa.getPrazoConclusao());
            tarefaParaAtualizar.setStatus(tarefa.isStatus());
            tarefaParaAtualizar.setUsuario(tarefa.getUsuario());
            return Optional.of(tarefaRepository.save(tarefaParaAtualizar));
        } else {
            return Optional.empty();
        }
    }

    public boolean excluir(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }
}
