package org.prj.catalog.web.repository;

import org.prj.catalog.web.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
