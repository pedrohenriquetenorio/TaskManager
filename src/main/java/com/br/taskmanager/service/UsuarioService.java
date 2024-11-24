package com.br.taskmanager.service;

import com.br.taskmanager.entity.Usuario;
import com.br.taskmanager.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    public void deletarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

}
