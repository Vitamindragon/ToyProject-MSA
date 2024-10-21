package org.prj.catalog.web.v2.repository;

import org.prj.catalog.web.v2.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
