package org.prj.catalog.web.v2.controller;

import lombok.RequiredArgsConstructor;
import org.prj.catalog.web.v2.dto.InventoryDTO;
import org.prj.catalog.web.v2.entity.Inventory;
import org.prj.catalog.web.v2.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.prj.catalog.web.v2.dto.InventoryDTO.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        Iterable<Inventory> inventoryList = inventoryService.findAllInventory();
        List<InventoryDTO> result = new ArrayList<>();
        inventoryList.forEach(v->{
                result.add(createInventoryDTO(v.getProductId(),v.getProductName(),v.getStock(), v.getUnitPrice(), v.getCreatedAt()));
        });

        return status(HttpStatus.OK).body(result);
    }


}
