package com.kimcura.kimcura_usuarios.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.kimcura.kimcura_usuarios.model.UsuarioModel;
import com.kimcura.kimcura_usuarios.reposistory.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void removeUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }   

}
