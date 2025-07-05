package br.com.catalog.product.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_PRODUCT")
@SequenceGenerator(sequenceName = "SQ_PRODUCT", allocationSize = 1, name = "product_seq_gen")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IMAGE_URL", length = 512)
    private String imageUrl;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "RATING_AVG")
    private Double ratingAvg;
}
