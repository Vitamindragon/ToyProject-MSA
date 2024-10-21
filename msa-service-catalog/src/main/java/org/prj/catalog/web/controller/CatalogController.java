package org.prj.catalog.web.controller;

import lombok.RequiredArgsConstructor;
import org.prj.catalog.web.dto.CatalogDTO;
import org.prj.catalog.web.entity.Catalog;
import org.prj.catalog.web.service.CatalogService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final Environment env;
    private final CatalogService catalogService;


    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in catalog Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }


    @GetMapping
    public ResponseEntity<List<CatalogDTO>> getAllInventory() {
        Iterable<Catalog> catalogList = catalogService.findAllInventory();
        List<CatalogDTO> result = new ArrayList<>();
        catalogList.forEach(v->{
                result.add(CatalogDTO.createCatalogDTO(v.getProductId(),v.getProductName(),v.getStock(), v.getUnitPrice(), v.getCreatedAt()));
        });

        return ResponseEntity.status(OK).body(result);
    }


}
