package com.almacen.service;

import com.almacen.model.dto.MarcaCreateOrUpdateDto;
import com.almacen.model.dto.MarcaResponseDto;
import com.almacen.model.entity.Marca;
import com.almacen.repository.MarcaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MarcaResponseDto createMarca(MarcaCreateOrUpdateDto marcaCreateOrUpdateDto) {
        Marca marca = modelMapper.map(marcaCreateOrUpdateDto, Marca.class);
        Marca savedMarca = marcaRepository.save(marca);
        return modelMapper.map(savedMarca, MarcaResponseDto.class);
    }

    @Override
    public MarcaResponseDto updateMarca(Integer id, MarcaCreateOrUpdateDto marcaCreateOrUpdateDto) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        if (optionalMarca.isPresent()) {
            Marca marca = optionalMarca.get();
            modelMapper.map(marcaCreateOrUpdateDto, marca);
            Marca updatedMarca = marcaRepository.save(marca);
            return modelMapper.map(updatedMarca, MarcaResponseDto.class);
        } else {
            throw new RuntimeException("Marca no encontrada");
        }
    }

    @Override
    public MarcaResponseDto getMarcaById(Integer id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        return marca.map(value -> modelMapper.map(value, MarcaResponseDto.class))
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
    }

    @Override
    public List<MarcaResponseDto> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcas.stream()
                .map(marca -> modelMapper.map(marca, MarcaResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMarca(Integer id) {
        marcaRepository.deleteById(id);
    }
}
