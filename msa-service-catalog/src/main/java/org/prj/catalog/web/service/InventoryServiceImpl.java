package org.prj.catalog.web.service;

import lombok.RequiredArgsConstructor;
import org.prj.catalog.web.entity.Catalog;
import org.prj.catalog.web.repository.CatalogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements CatalogService{
    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<Catalog> findAllInventory() {
        return catalogRepository.findAll();
    }
}
