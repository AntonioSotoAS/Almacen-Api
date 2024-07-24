package com.almacen.config;

import com.almacen.model.dto.DetalleSolicitudRequestDto;
import com.almacen.model.entity.DetalleSolicitud;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.almacen.model.dto.SolicitudResponseDto;
import com.almacen.model.entity.Solicitud;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Solicitud, SolicitudResponseDto>() {
            @Override
            protected void configure() {
                map().setDependencia(source.getDependencia().getNombreDependencia());
            }
        });

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Configurar el mapeo explícito para DetalleSolicitud y DetalleSolicitudRequestDto
        modelMapper.typeMap(DetalleSolicitudRequestDto.class, DetalleSolicitud.class).addMappings(mapper -> {
            mapper.map(DetalleSolicitudRequestDto::getIdProducto, (dest, v) -> dest.getProducto().setId_producto((Integer) v));
            mapper.map(DetalleSolicitudRequestDto::getIdSolicitud, (dest, v) -> dest.getSolicitud().setId((Integer) v));
            mapper.skip(DetalleSolicitud::setId); // Ignorar el mapeo automático del id
        });

        return modelMapper;
    }
}
