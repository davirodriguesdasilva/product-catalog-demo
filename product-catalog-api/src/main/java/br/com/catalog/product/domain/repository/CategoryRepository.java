package br.com.catalog.product.domain.repository;

import br.com.catalog.product.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
