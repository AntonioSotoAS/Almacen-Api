package com.almacen.service;

import com.almacen.model.dto.UnidadMedidaCreateOrUpdateDto;
import com.almacen.model.dto.UnidadMedidaResponseDto;
import com.almacen.model.entity.UnidadMedida;
import com.almacen.repository.UnidadMedidaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UnidadMedidaResponseDto createUnidadMedida(UnidadMedidaCreateOrUpdateDto unidadMedidaCreateOrUpdateDto) {
        UnidadMedida unidadMedida = modelMapper.map(unidadMedidaCreateOrUpdateDto, UnidadMedida.class);
        UnidadMedida savedUnidadMedida = unidadMedidaRepository.save(unidadMedida);
        return modelMapper.map(savedUnidadMedida, UnidadMedidaResponseDto.class);
    }

    @Override
    public UnidadMedidaResponseDto updateUnidadMedida(Integer id, UnidadMedidaCreateOrUpdateDto unidadMedidaCreateOrUpdateDto) {
        Optional<UnidadMedida> optionalUnidadMedida = unidadMedidaRepository.findById(id);
        if (optionalUnidadMedida.isPresent()) {
            UnidadMedida unidadMedida = optionalUnidadMedida.get();
            modelMapper.map(unidadMedidaCreateOrUpdateDto, unidadMedida);
            UnidadMedida updatedUnidadMedida = unidadMedidaRepository.save(unidadMedida);
            return modelMapper.map(updatedUnidadMedida, UnidadMedidaResponseDto.class);
        } else {
            throw new RuntimeException("Unidad de Medida no encontrada");
        }
    }

    @Override
    public UnidadMedidaResponseDto getUnidadMedidaById(Integer id) {
        Optional<UnidadMedida> unidadMedida = unidadMedidaRepository.findById(id);
        return unidadMedida.map(value -> modelMapper.map(value, UnidadMedidaResponseDto.class))
                .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"));
    }

    @Override
    public List<UnidadMedidaResponseDto> getAllUnidadMedidas() {
        List<UnidadMedida> unidadMedidas = unidadMedidaRepository.findAll();
        return unidadMedidas.stream()
                .map(unidadMedida -> modelMapper.map(unidadMedida, UnidadMedidaResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUnidadMedida(Integer id) {
        unidadMedidaRepository.deleteById(id);
    }
}
