package org.prj.catalog.web.v1.service;

import org.prj.catalog.web.v1.entity.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}