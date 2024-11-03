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

@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final Environment env;
    private final CatalogService catalogService;

    // 서비스 상태 확인
    @GetMapping("/status")
    public String status() {
        return String.format("It's Working in Catalog Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    // 전체 인벤토리 조회
    @GetMapping
    public ResponseEntity<List<CatalogDTO>> getAllInventory() {
        Iterable<Catalog> catalogList = catalogService.getAllCatalogs();
        List<CatalogDTO> result = new ArrayList<>();

        catalogList.forEach(v -> result.add(
                        CatalogDTO.createCatalogDTO(
                                v.getProductId(),
                                v.getProductName(),
                                v.getStock(),
                                v.getUnitPrice(),
                                v.getCreatedAt())
                )
        );

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
