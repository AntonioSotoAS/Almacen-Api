package com.almacen.service;

import com.almacen.model.dto.CategoriaCreateOrUpdateDto;
import com.almacen.model.dto.CategoriaResponseDto;
import com.almacen.model.entity.Categoria;
import com.almacen.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoriaResponseDto createCategoria(CategoriaCreateOrUpdateDto categoriaCreateOrUpdateDto) {
        Categoria categoria = modelMapper.map(categoriaCreateOrUpdateDto, Categoria.class);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(savedCategoria, CategoriaResponseDto.class);
    }

    @Override
    public CategoriaResponseDto updateCategoria(Integer id, CategoriaCreateOrUpdateDto categoriaCreateOrUpdateDto) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            modelMapper.map(categoriaCreateOrUpdateDto, categoria);
            Categoria updatedCategoria = categoriaRepository.save(categoria);
            return modelMapper.map(updatedCategoria, CategoriaResponseDto.class);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }

    @Override
    public CategoriaResponseDto getCategoriaById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(value -> modelMapper.map(value, CategoriaResponseDto.class))
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    @Override
    public List<CategoriaResponseDto> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
