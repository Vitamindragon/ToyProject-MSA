package org.prj.catalog.web.service;


import org.prj.catalog.web.entity.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
