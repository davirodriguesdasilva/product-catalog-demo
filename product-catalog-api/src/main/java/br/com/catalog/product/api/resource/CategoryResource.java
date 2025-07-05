package br.com.catalog.product.api.resource;

import br.com.catalog.product.domain.model.Category;
import br.com.catalog.product.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> allCategories() {

        return ResponseEntity.ok(categoryService.allCategories());
    }
}
