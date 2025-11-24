package com.kimcura.kimcura_usuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.kimcura.kimcura_usuarios.model.UsuarioModel;

public interface UsuarioService {

    UsuarioModel createUsuario(UsuarioModel usuario);
    Optional<UsuarioModel> getUsuarioById(UUID id);
    List<UsuarioModel> getAllUsuarios();
    Boolean removeUsuarioById(UUID id);
    UsuarioModel duplicateUsuarioById(UUID id);

}
