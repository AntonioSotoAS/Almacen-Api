package com.almacen.service;

import com.almacen.model.dto.RegisterRequest;
import com.almacen.model.dto.UsuarioCreateOrUpdateDto;
import com.almacen.model.dto.UsuarioResponseDto;
import com.almacen.model.entity.Usuario;
import com.almacen.repository.UsuarioRepository;
import com.almacen.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, @Lazy PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getClave(),
                List.of(new SimpleGrantedAuthority(usuario.getRol().name()))
        );
    }

    @Override
    public UsuarioResponseDto createUsuario(UsuarioCreateOrUpdateDto usuarioCreateOrUpdateDto) {
        validateEmail(usuarioCreateOrUpdateDto.getEmail());
        validatePassword(usuarioCreateOrUpdateDto.getClave());

        Usuario usuario = modelMapper.map(usuarioCreateOrUpdateDto, Usuario.class);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(savedUsuario, UsuarioResponseDto.class);
    }

    @Override
    public UsuarioResponseDto updateUsuario(Integer id, UsuarioCreateOrUpdateDto usuarioCreateOrUpdateDto) {
        validateEmail(usuarioCreateOrUpdateDto.getEmail());
        if (usuarioCreateOrUpdateDto.getClave() != null) {
            validatePassword(usuarioCreateOrUpdateDto.getClave());
        }

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (usuarioCreateOrUpdateDto.getClave() != null) {
                usuario.setClave(passwordEncoder.encode(usuarioCreateOrUpdateDto.getClave()));
            }
            modelMapper.map(usuarioCreateOrUpdateDto, usuario);
            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return modelMapper.map(updatedUsuario, UsuarioResponseDto.class);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public UsuarioResponseDto getUsuarioById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(value -> modelMapper.map(value, UsuarioResponseDto.class))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<UsuarioResponseDto> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void register(RegisterRequest request) {
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setClave(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Usuario.Rol.valueOf(request.getRol()));
        usuario.setEstado(Usuario.Estado.HABILITADO);
        usuarioRepository.save(usuario);
    }

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new RuntimeException("Correo electrónico no es válido");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("La contraseña debe tener al menos 8 caracteres");
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


}
