package br.com.catalog.product.domain.service.impl;

import br.com.catalog.product.api.model.input.ProductFilterRequest;
import br.com.catalog.product.domain.model.Product;
import br.com.catalog.product.domain.repository.ProductRepository;
import br.com.catalog.product.domain.repository.spec.ProductSpecifications;
import br.com.catalog.product.domain.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> listInfinite(ProductFilterRequest filter, Long lastId, int size) {

        Specification<Product> spec = ProductSpecifications.byFilter(filter, lastId);

        Pageable page = PageRequest.of(0, size, Sort.by("id").ascending());
        return productRepository.findAll(spec, page).getContent();
    }
}
