package br.com.catalog.product.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_CATEGORY")
@SequenceGenerator(sequenceName = "SQ_CATEGORY", allocationSize = 1, name = "category_seq_gen")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
}
