package com.almacen.controller;


import com.almacen.model.dto.UsuarioCreateOrUpdateDto;
import com.almacen.model.dto.UsuarioResponseDto;
import com.almacen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUsuario(@RequestBody UsuarioCreateOrUpdateDto usuarioCreateOrUpdateDto) {
        UsuarioResponseDto createdUsuario = usuarioService.createUsuario(usuarioCreateOrUpdateDto);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioCreateOrUpdateDto usuarioCreateOrUpdateDto) {
        UsuarioResponseDto updatedUsuario = usuarioService.updateUsuario(id, usuarioCreateOrUpdateDto);
        return ResponseEntity.ok(updatedUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getUsuarioById(@PathVariable Integer id) {
        UsuarioResponseDto usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAllUsuarios() {
        List<UsuarioResponseDto> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
