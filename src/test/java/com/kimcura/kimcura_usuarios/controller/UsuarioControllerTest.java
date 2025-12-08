package com.kimcura.kimcura_usuarios.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import com.kimcura.kimcura_usuarios.model.UsuarioModel;
import com.kimcura.kimcura_usuarios.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    private UsuarioController usuarioController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        usuarioController = new UsuarioController(usuarioService, null);
    }

    @Test
    void getAllUsuarioModel_returnsList() {
        UsuarioModel u = new UsuarioModel();
        u.setEmail("c@test.com");
        when(usuarioService.getAllUsuarios()).thenReturn(Arrays.asList(u));

        var result = usuarioController.getAllUsuarioModel();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getUsuarioById_returnsOptional() {
        UUID id = UUID.randomUUID();
        UsuarioModel u = new UsuarioModel();
        u.setEmail("single@test.com");
        when(usuarioService.getUsuarioById(id)).thenReturn(Optional.of(u));

        Optional<UsuarioModel> res = usuarioController.getUsuarioById(id);
        assertTrue(res.isPresent());
        assertEquals("single@test.com", res.get().getEmail());
    }

    @Test
    void removeUsuarioModel_returnsOkOrNotFound() {
        UUID id = UUID.randomUUID();
        when(usuarioService.removeUsuarioById(id)).thenReturn(true);
        ResponseEntity.BodyBuilder builder = usuarioController.removeUsuarioModel(id);
        ResponseEntity<?> response = builder.build();
        assertEquals(200, response.getStatusCode().value());

        UUID id2 = UUID.randomUUID();
        when(usuarioService.removeUsuarioById(id2)).thenReturn(false);
        ResponseEntity.BodyBuilder builder2 = usuarioController.removeUsuarioModel(id2);
        ResponseEntity<?> response2 = builder2.build();
        assertEquals(404, response2.getStatusCode().value());
    }

    @Test
    void duplicateUsuarioById_returnsModel() {
        UUID id = UUID.randomUUID();
        UsuarioModel copy = new UsuarioModel();
        copy.setEmail("dup@test.com");
        when(usuarioService.duplicateUsuarioById(id)).thenReturn(copy);

        UsuarioModel res = usuarioController.duplicateUsuarioById(id);
        assertNotNull(res);
        assertEquals("dup@test.com", res.getEmail());
    }

    @Test
    void setUsuario_delegatesToService() {
        UsuarioModel u = new UsuarioModel();
        u.setEmail("new@test.com");
        when(usuarioService.createUsuario(u)).thenReturn(u);

        UsuarioModel res = usuarioController.setUsuario(u);
        assertNotNull(res);
        assertEquals("new@test.com", res.getEmail());
    }

}
