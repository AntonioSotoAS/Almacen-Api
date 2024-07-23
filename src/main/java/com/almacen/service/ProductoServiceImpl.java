package com.almacen.service;

import com.almacen.model.dto.ProductoRequestDto;
import com.almacen.model.dto.ProductoResponseDto;
import com.almacen.model.entity.Producto;
import com.almacen.repository.CategoriaRepository;
import com.almacen.repository.MarcaRepository;
import com.almacen.repository.ProductoRepository;
import com.almacen.repository.UnidadMedidaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductoResponseDto createProducto(ProductoRequestDto productoRequestDto) {
        Producto producto = new Producto();
        producto.setMarca(marcaRepository.findById(productoRequestDto.getId_marca()).orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        producto.setCategoria(categoriaRepository.findById(productoRequestDto.getId_categoria()).orElseThrow(() -> new RuntimeException("Categoria no encontrada")));
        producto.setUnidadMedida(unidadMedidaRepository.findById(productoRequestDto.getId_unimed()).orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada")));
        producto.setCod(productoRequestDto.getCod());
        producto.setDescripcion(productoRequestDto.getDescripcion());
        producto.setPrecio(productoRequestDto.getPrecio());
        producto.setEstado(productoRequestDto.getEstado());

        Producto savedProducto = productoRepository.save(producto);
        return mapToResponseDto(savedProducto);
    }

    @Override
    public ProductoResponseDto updateProducto(Integer id, ProductoRequestDto productoRequestDto) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setMarca(marcaRepository.findById(productoRequestDto.getId_marca()).orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        producto.setCategoria(categoriaRepository.findById(productoRequestDto.getId_categoria()).orElseThrow(() -> new RuntimeException("Categoria no encontrada")));
        producto.setUnidadMedida(unidadMedidaRepository.findById(productoRequestDto.getId_unimed()).orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada")));
        producto.setCod(productoRequestDto.getCod());
        producto.setDescripcion(productoRequestDto.getDescripcion());
        producto.setPrecio(productoRequestDto.getPrecio());
        producto.setEstado(productoRequestDto.getEstado());

        Producto updatedProducto = productoRepository.save(producto);
        return mapToResponseDto(updatedProducto);
    }

    @Override
    public ProductoResponseDto getProductoById(Integer id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToResponseDto(producto);
    }

    @Override
    public List<ProductoResponseDto> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    private ProductoResponseDto mapToResponseDto(Producto producto) {
        ProductoResponseDto dto = modelMapper.map(producto, ProductoResponseDto.class);
        dto.setMarca(producto.getMarca().getNombre_marca());
        dto.setCategoria(producto.getCategoria().getNombre_categoria());
        dto.setUnidadMedida(producto.getUnidadMedida().getTipo_unidad());
        return dto;
    }
}
