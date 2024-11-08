package com.sparta.gaeppa.product.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "p_products")
@Getter
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory category;

    @Builder
    protected Product(String name, String description, int price, boolean hideStatus, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.hideStatus = hideStatus;
        this.category = category;
    }
}
