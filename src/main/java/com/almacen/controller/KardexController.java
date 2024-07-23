package com.almacen.controller;

import com.almacen.model.dto.KardexResponseDto;
import com.almacen.service.KardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kardex")
public class KardexController {

    @Autowired
    private KardexService kardexService;

    @GetMapping("/{idProducto}")
    public ResponseEntity<KardexResponseDto> getKardex(@PathVariable Integer idProducto) {
        KardexResponseDto kardex = kardexService.getKardex(idProducto);
        return ResponseEntity.ok(kardex);
    }
}
