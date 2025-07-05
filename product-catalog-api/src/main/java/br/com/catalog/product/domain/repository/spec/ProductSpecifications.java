package br.com.catalog.product.domain.repository.spec;

import br.com.catalog.product.api.model.input.ProductFilterRequest;
import br.com.catalog.product.domain.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecifications {

    private ProductSpecifications() {
    }

    public static Specification<Product> byFilter(ProductFilterRequest f, Long lastId) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (f.getCategoryId() != null) {
                predicates.add(cb.equal(root.get("category").get("id"),
                        f.getCategoryId()));
            }
            if (f.getMinPrice() != null) {
                predicates.add(cb.ge(root.get("price"), f.getMinPrice()));
            }
            if (f.getMaxPrice() != null) {
                predicates.add(cb.le(root.get("price"), f.getMaxPrice()));
            }
            if (f.getMinRating() != null) {
                predicates.add(cb.ge(root.get("ratingAvg"), f.getMinRating()));
            }
            if (f.getMaxRating() != null) {
                predicates.add(cb.le(root.get("ratingAvg"), f.getMaxRating()));
            }
            if (lastId != null) {
                predicates.add(cb.greaterThan(root.get("id"), lastId));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
