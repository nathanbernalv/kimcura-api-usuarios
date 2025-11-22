package com.kimcura.kimcura_usuarios.service;

import java.util.List;
import java.util.Optional;

import com.kimcura.kimcura_usuarios.model.UsuarioModel;

public interface UsuarioService {

    UsuarioModel createUsuario(UsuarioModel usuario);
    Optional<UsuarioModel> getUsuarioById(Long id);
    List<UsuarioModel> getAllUsuarios();
    void removeUsuarioById(Long id);

}
