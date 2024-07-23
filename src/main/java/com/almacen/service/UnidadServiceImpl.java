package com.almacen.service.impl;

import com.almacen.model.dto.UnidadRequestDto;
import com.almacen.model.dto.UnidadResponseDto;
import com.almacen.model.entity.Dependencia;
import com.almacen.model.entity.Proveedor;
import com.almacen.model.entity.Unidad;
import com.almacen.model.entity.Usuario;
import com.almacen.repository.DependenciaRepository;
import com.almacen.repository.ProveedorRepository;
import com.almacen.repository.UnidadRepository;
import com.almacen.repository.UsuarioRepository;
import com.almacen.service.UnidadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadServiceImpl implements UnidadService {

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DependenciaRepository dependenciaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UnidadResponseDto createUnidad(UnidadRequestDto unidadRequestDto) {
        Unidad unidad = new Unidad();
        unidad.setJefeUnidad(unidadRequestDto.getJefeUnidad());
        unidad.setNombreUnidad(unidadRequestDto.getNombreUnidad());
        unidad.setEstado(Unidad.Estado.valueOf(unidadRequestDto.getEstado()));

        Usuario usuario = usuarioRepository.findById(unidadRequestDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        unidad.setUsuario(usuario);

        Dependencia dependencia = dependenciaRepository.findById(unidadRequestDto.getIdDependencia())
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
        unidad.setDependencia(dependencia);

        Proveedor proveedor = proveedorRepository.findById(unidadRequestDto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        unidad.setProveedor(proveedor);

        unidad = unidadRepository.save(unidad);
        return convertToDto(unidad);
    }

    @Override
    public UnidadResponseDto updateUnidad(Integer id, UnidadRequestDto unidadRequestDto) {
        Unidad unidad = unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));
        unidad.setJefeUnidad(unidadRequestDto.getJefeUnidad());
        unidad.setNombreUnidad(unidadRequestDto.getNombreUnidad());
        unidad.setEstado(Unidad.Estado.valueOf(unidadRequestDto.getEstado()));

        Usuario usuario = usuarioRepository.findById(unidadRequestDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        unidad.setUsuario(usuario);

        Dependencia dependencia = dependenciaRepository.findById(unidadRequestDto.getIdDependencia())
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
        unidad.setDependencia(dependencia);

        Proveedor proveedor = proveedorRepository.findById(unidadRequestDto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        unidad.setProveedor(proveedor);

        unidad = unidadRepository.save(unidad);
        return convertToDto(unidad);
    }

    @Override
    public UnidadResponseDto getUnidadById(Integer id) {
        Unidad unidad = unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));
        return convertToDto(unidad);
    }

    @Override
    public List<UnidadResponseDto> getAllUnidades() {
        List<Unidad> unidades = unidadRepository.findAll();
        return unidades.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUnidad(Integer id) {
        unidadRepository.deleteById(id);
    }

    private UnidadResponseDto convertToDto(Unidad unidad) {
        UnidadResponseDto unidadResponseDto = modelMapper.map(unidad, UnidadResponseDto.class);
        unidadResponseDto.setUsuarioNombre(unidad.getUsuario().getUsername());
        unidadResponseDto.setDependenciaNombre(unidad.getDependencia().getNombreDependencia());
        unidadResponseDto.setProveedorNombre(unidad.getProveedor().getNombreProv());
        return unidadResponseDto;
    }
}
