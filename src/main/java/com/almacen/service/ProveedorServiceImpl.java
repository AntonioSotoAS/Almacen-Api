package com.almacen.service;

import com.almacen.model.dto.*;
import com.almacen.model.entity.Proveedor;
import com.almacen.repository.ProveedorRepository;
import com.almacen.service.ProveedorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProveedorResponseDto createProveedor(ProveedorRequestDto proveedorRequestDto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProv(proveedorRequestDto.getNombreProv());
        proveedor.setEstado(Proveedor.Estado.valueOf(proveedorRequestDto.getEstado()));

        proveedor = proveedorRepository.save(proveedor);
        return convertToDto(proveedor);
    }

    @Override
    public ProveedorResponseDto updateProveedor(Integer id, ProveedorRequestDto proveedorRequestDto) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        proveedor.setNombreProv(proveedorRequestDto.getNombreProv());
        proveedor.setEstado(Proveedor.Estado.valueOf(proveedorRequestDto.getEstado()));

        proveedor = proveedorRepository.save(proveedor);
        return convertToDto(proveedor);
    }

    @Override
    public ProveedorResponseDto getProveedorById(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return convertToDto(proveedor);
    }

    @Override
    public List<ProveedorResponseDto> getAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }

    private ProveedorResponseDto convertToDto(Proveedor proveedor) {
        ProveedorResponseDto dto = modelMapper.map(proveedor, ProveedorResponseDto.class);

        if (proveedor.getUnidades() != null) {
            dto.setUnidades(proveedor.getUnidades().stream()
                    .map(unidad -> {
                        UnidadResponseDto unidadDto = modelMapper.map(unidad, UnidadResponseDto.class);
                        unidadDto.setIdUsuario(unidad.getUsuario().getId());
                        unidadDto.setUsuarioNombre(unidad.getUsuario().getUsername());
                        unidadDto.setIdDependencia(unidad.getDependencia().getId_dependencia());
                        unidadDto.setDependenciaNombre(unidad.getDependencia().getNombreDependencia());
                        unidadDto.setIdProveedor(unidad.getProveedor().getId_proveedor());
                        unidadDto.setProveedorNombre(unidad.getProveedor().getNombreProv());
                        return unidadDto;
                    }).collect(Collectors.toSet()));
        }

        if (proveedor.getNotasIngreso() != null) {
            dto.setNotasIngreso(proveedor.getNotasIngreso().stream()
                    .map(notaIngreso -> {
                        NotaIngresoResponseDto notaIngresoDto = modelMapper.map(notaIngreso, NotaIngresoResponseDto.class);
                        notaIngresoDto.setIdProveedor(notaIngreso.getProveedor().getId_proveedor());
                        notaIngresoDto.setProveedorNombre(notaIngreso.getProveedor().getNombreProv());
                        return notaIngresoDto;
                    }).collect(Collectors.toSet()));
        }

        return dto;
    }
}
