package com.kimcura.kimcura_usuarios.reposistory;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kimcura.kimcura_usuarios.model.UsuarioModel;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    @Modifying
    @Transactional
    @Query("SELECT u FROM UsuarioModel u WHERE u.id = ?1") 
    Optional<UsuarioModel> getUsuarioById(UUID id);

}
