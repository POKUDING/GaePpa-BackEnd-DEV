package com.sparta.gaeppa.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "p_product_options")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_option_id")
    private UUID id;

    @Column(name = "product_option_name")
    private String name;

    @Column(name = "product_option_price")
    private Double price;

//    @ManyToOne
//    @JoinColumn(name = "product_option_category_id", nullable = false)
//    private ProductOptionCategory optionCategory;
}