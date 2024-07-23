package com.almacen.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

        return modelMapper;
    }
}
