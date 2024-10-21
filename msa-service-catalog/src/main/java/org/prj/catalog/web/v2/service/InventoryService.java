package org.prj.catalog.web.v2.service;

import org.prj.catalog.web.v2.entity.Inventory;

public interface InventoryService {
    Iterable<Inventory> findAllInventory();
}
