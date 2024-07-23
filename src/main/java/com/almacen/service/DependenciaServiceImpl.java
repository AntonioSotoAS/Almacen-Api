package com.almacen.service;

import com.almacen.model.dto.DependenciaRequestDto;
import com.almacen.model.dto.DependenciaResponseDto;
import com.almacen.model.entity.Dependencia;
import com.almacen.repository.DependenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependenciaServiceImpl implements DependenciaService {

    @Autowired
    private DependenciaRepository dependenciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DependenciaResponseDto createDependencia(DependenciaRequestDto dependenciaRequestDto) {
        Dependencia dependencia = modelMapper.map(dependenciaRequestDto, Dependencia.class);
        Dependencia savedDependencia = dependenciaRepository.save(dependencia);
        return modelMapper.map(savedDependencia, DependenciaResponseDto.class);
    }

    @Override
    public DependenciaResponseDto updateDependencia(Integer id, DependenciaRequestDto dependenciaRequestDto) {
        Dependencia dependencia = dependenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
        modelMapper.map(dependenciaRequestDto, dependencia);
        Dependencia updatedDependencia = dependenciaRepository.save(dependencia);
        return modelMapper.map(updatedDependencia, DependenciaResponseDto.class);
    }

    @Override
    public DependenciaResponseDto getDependenciaById(Integer id) {
        Dependencia dependencia = dependenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
        return modelMapper.map(dependencia, DependenciaResponseDto.class);
    }

    @Override
    public List<DependenciaResponseDto> getAllDependencias() {
        List<Dependencia> dependencias = dependenciaRepository.findAll();
        return dependencias.stream()
                .map(dependencia -> modelMapper.map(dependencia, DependenciaResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDependencia(Integer id) {
        dependenciaRepository.deleteById(id);
    }
}
