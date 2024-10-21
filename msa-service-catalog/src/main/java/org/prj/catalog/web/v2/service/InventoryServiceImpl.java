package org.prj.catalog.web.v2.service;

import lombok.RequiredArgsConstructor;
import org.prj.catalog.web.v2.entity.Inventory;
import org.prj.catalog.web.v2.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;

    @Override
    public Iterable<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }
}
