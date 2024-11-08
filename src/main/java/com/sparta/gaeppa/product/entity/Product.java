package com.sparta.gaeppa.product.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "p_products")
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    private UUID id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Column(name = "product_price", nullable = false)
    private int price;

    @Column(name = "product_hide_status", nullable = false)
    private boolean hideStatus;
}
