package com.kimcura.kimcura_usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimcura.kimcura_usuarios.model.UsuarioModel;
import com.kimcura.kimcura_usuarios.reposistory.UsuarioRepository;
import com.kimcura.kimcura_usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public List<UsuarioModel> getAllUsuarioModel() {
        return usuarioService.getAllUsuarios();
    }
    
    @DeleteMapping("/{usuarioId}")
    public void removeUsuarioModel(@RequestParam Long usuarioId) {
        usuarioService.removeUsuarioById(usuarioId);
    }
    
}
