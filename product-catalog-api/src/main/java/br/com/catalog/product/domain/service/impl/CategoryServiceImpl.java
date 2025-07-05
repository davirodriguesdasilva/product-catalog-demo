package br.com.catalog.product.domain.service.impl;

import br.com.catalog.product.domain.model.Category;
import br.com.catalog.product.domain.repository.CategoryRepository;
import br.com.catalog.product.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
}
