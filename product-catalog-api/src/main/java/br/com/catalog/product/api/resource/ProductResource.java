package br.com.catalog.product.api.resource;

import br.com.catalog.product.api.model.input.ProductFilterRequest;
import br.com.catalog.product.domain.model.Product;
import br.com.catalog.product.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> infiniteScroll(
            @Valid ProductFilterRequest filter,
            @RequestParam(required = false) Long lastId,
            @RequestParam(defaultValue = "20") int size) {

        return ResponseEntity.ok(productService.listInfinite(filter, lastId, size));
    }
}
