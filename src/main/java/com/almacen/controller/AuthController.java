package com.almacen.controller;

import com.almacen.model.dto.AuthenticationRequest;
import com.almacen.model.dto.AuthenticationResponse;
import com.almacen.model.dto.RegisterRequest;
import com.almacen.model.dto.UsuarioResponseDto;
import com.almacen.model.entity.Usuario;
import com.almacen.security.JwtUtil;
import com.almacen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = usuarioService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails, userDetails.getAuthorities().iterator().next().getAuthority());

        Usuario usuario = usuarioService.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        AuthenticationResponse response = new AuthenticationResponse(
                jwt,
                usuario.getEmail(),
                usuario.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority()
        );

        // Asegúrate de incluir todos los datos necesarios del usuario en la respuesta
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("accessToken", jwt);
        responseBody.put("userData", response); // Incluye los datos del usuario aquí

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        usuarioService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDto> getAuthenticatedUser() {
        Usuario usuario = usuarioService.getAuthenticatedUser();
        UsuarioResponseDto userResponseDto = new UsuarioResponseDto();
        userResponseDto.setId_usuario(usuario.getId());
        userResponseDto.setUsername(usuario.getUsername());
        userResponseDto.setEmail(usuario.getEmail());
        userResponseDto.setRol(usuario.getRol().name());
        return ResponseEntity.ok(userResponseDto);
    }

}
