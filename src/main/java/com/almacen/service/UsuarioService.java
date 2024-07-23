package com.almacen.service;

import com.almacen.model.dto.RegisterRequest;
import com.almacen.model.dto.UsuarioCreateOrUpdateDto;
import com.almacen.model.dto.UsuarioResponseDto;
import com.almacen.model.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends UserDetailsService {

    UsuarioResponseDto createUsuario(UsuarioCreateOrUpdateDto usuarioCreateOrUpdateDto);

    UsuarioResponseDto updateUsuario(Integer id, UsuarioCreateOrUpdateDto usuarioCreateOrUpdateDto);

    UsuarioResponseDto getUsuarioById(Integer id);

    List<UsuarioResponseDto> getAllUsuarios();

    void deleteUsuario(Integer id);

    void register(RegisterRequest request);

    Optional<Usuario> findByEmail(String email);
}
