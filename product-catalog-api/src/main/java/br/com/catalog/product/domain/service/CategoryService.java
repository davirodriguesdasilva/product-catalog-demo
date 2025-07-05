package br.com.catalog.product.domain.service;

import br.com.catalog.product.domain.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> allCategories();
}
