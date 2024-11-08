package com.sparta.gaeppa.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "p_product_option_categories")
public class ProductOptionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_option_category_id")
    private UUID id;

    @Column(name = "product_option_category_name")
    private String name;

    @Column(name = "product_option_max_limits")
    private Integer maxLimits;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;

//    @OneToMany(mappedBy = "optionCategory")
//    private List<ProductOption> options;

    // getters and setters
}