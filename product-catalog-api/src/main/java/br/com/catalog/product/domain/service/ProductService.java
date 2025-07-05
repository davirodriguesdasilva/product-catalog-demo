package br.com.catalog.product.domain.service;

import br.com.catalog.product.api.model.input.ProductFilterRequest;
import br.com.catalog.product.domain.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> listInfinite(ProductFilterRequest filter, Long lastId, int size);
}
