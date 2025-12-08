package com.kimcura.kimcura_usuarios.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kimcura.kimcura_usuarios.model.UsuarioModel;
import com.kimcura.kimcura_usuarios.reposistory.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioModel usuario;

    @BeforeEach
    void setUp() {
        usuario = new UsuarioModel();
        usuario.setEmail("test@example.com");
        usuario.setFirstName("John");
        usuario.setLastName("Doe");
        usuario.setIsActive(true);
        usuario.setRole("USER");
        usuario.setAzureEntraId("entra-id-123");
        usuario.setSchoolId(UUID.randomUUID());
    }

    @Test
    void createUsuario_savesAndReturns() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel saved = usuarioService.createUsuario(usuario);

        assertNotNull(saved);
        assertEquals("test@example.com", saved.getEmail());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void getUsuarioById_found() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.getUsuarioById(id)).thenReturn(Optional.of(usuario));

        Optional<UsuarioModel> opt = usuarioService.getUsuarioById(id);
        assertTrue(opt.isPresent());
        assertEquals("test@example.com", opt.get().getEmail());
    }

    @Test
    void getUsuarioById_notFound() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.getUsuarioById(id)).thenReturn(Optional.empty());

        Optional<UsuarioModel> opt = usuarioService.getUsuarioById(id);
        assertFalse(opt.isPresent());
    }

    @Test
    void getAllUsuarios_returnsList() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        List<UsuarioModel> list = usuarioService.getAllUsuarios();
        assertEquals(1, list.size());
    }

    @Test
    void removeUsuarioById_success() {
        UUID id = UUID.randomUUID();
        // default: do nothing
        Boolean result = usuarioService.removeUsuarioById(id);
        assertTrue(result);
        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void removeUsuarioById_notFound_returnsFalse() {
        UUID id = UUID.randomUUID();
        doThrow(new EmptyResultDataAccessException(1)).when(usuarioRepository).deleteById(id);

        Boolean result = usuarioService.removeUsuarioById(id);
        assertFalse(result);
    }

    @Test
    void duplicateUsuarioById_present_createsCopy() {
        UUID id = UUID.randomUUID();
        UsuarioModel original = usuario;
        when(usuarioRepository.getUsuarioById(id)).thenReturn(Optional.of(original));

        UsuarioModel savedCopy = new UsuarioModel();
        savedCopy.setId(UUID.randomUUID());
        savedCopy.setEmail(original.getEmail() + "_copy_" + id.toString());
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(savedCopy);

        UsuarioModel result = usuarioService.duplicateUsuarioById(id);
        assertNotNull(result);
        assertTrue(result.getEmail().contains("_copy_"));
        verify(usuarioRepository).save(any(UsuarioModel.class));
    }

    @Test
    void duplicateUsuarioById_notPresent_returnsNull() {
        UUID id = UUID.randomUUID();
        when(usuarioRepository.getUsuarioById(id)).thenReturn(Optional.empty());

        UsuarioModel result = usuarioService.duplicateUsuarioById(id);
        assertNull(result);
    }

    @Test
    void setUsuario_usesSave() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel result = usuarioService.setUsuario(usuario);
        assertNotNull(result);
        verify(usuarioRepository).save(usuario);
    }

}
