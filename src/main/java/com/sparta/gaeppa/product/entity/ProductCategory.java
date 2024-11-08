package com.sparta.gaeppa.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "p_product_categories")
public class ProductCategory {

    @Id
    @Column(name = "product_category_id")
    private UUID id;

    @Column(name = "product_category_name")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "store_id", nullable = false)
//    private Store store;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products;

}