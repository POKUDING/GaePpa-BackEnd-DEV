package com.sparta.gaeppa.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "p_product_pictures")
public class ProductPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_picture_id")
    private UUID id;

    @Column(name = "product_picture_name")
    private String name;

    @Column(name = "product_picture_path")
    private String path;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
}