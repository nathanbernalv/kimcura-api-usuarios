package com.kimcura.kimcura_usuarios.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimcura.kimcura_usuarios.model.UsuarioModel;
import com.kimcura.kimcura_usuarios.reposistory.UsuarioRepository;
import com.kimcura.kimcura_usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioModel> getAllUsuarioModel() {
        return usuarioService.getAllUsuarios();
    }
    
    @GetMapping("{id}")
    public Optional<UsuarioModel> getUsuarioById(@PathVariable UUID id) {
        return usuarioService.getUsuarioById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity.BodyBuilder removeUsuarioModel(@PathVariable UUID id) {   
        return usuarioService.removeUsuarioById(id) ? ResponseEntity.ok(): ResponseEntity.status(404);
    }
  
    @PutMapping("duplicate/{id}")
    public UsuarioModel duplicateUsuarioById(@PathVariable UUID id) {
        return usuarioService.duplicateUsuarioById(id);
    }

    @PutMapping
    public UsuarioModel setUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.createUsuario(usuario);
    }

}
