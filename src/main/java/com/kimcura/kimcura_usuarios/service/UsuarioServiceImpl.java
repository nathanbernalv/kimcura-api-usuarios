package com.kimcura.kimcura_usuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
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
    public Optional<UsuarioModel> getUsuarioById(UUID id) {
        return usuarioRepository.getUsuarioById(id);
    }

    @Override
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Boolean removeUsuarioById(UUID id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }   
  
    @Override
    public UsuarioModel duplicateUsuarioById(UUID id) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.getUsuarioById(id);
        if (usuarioOpt.isPresent()) {
            UsuarioModel originalUsuario = usuarioOpt.get();
            UsuarioModel duplicatedUsuario = new UsuarioModel();
            duplicatedUsuario.setAzureEntraId(originalUsuario.getAzureEntraId() + "_copy_" + id.toString() );
            duplicatedUsuario.setSchoolId(originalUsuario.getSchoolId());
            duplicatedUsuario.setRole(originalUsuario.getRole());
            duplicatedUsuario.setEmail(originalUsuario.getEmail() + "_copy_" + id.toString());
            duplicatedUsuario.setFirstName(originalUsuario.getFirstName());
            duplicatedUsuario.setLastName(originalUsuario.getLastName());
            duplicatedUsuario.setIsActive(originalUsuario.getIsActive());
            return usuarioRepository.save(duplicatedUsuario);
        }
        return null;
    }

    public UsuarioModel setUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }
    

}
