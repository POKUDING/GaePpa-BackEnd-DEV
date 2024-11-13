package com.sparta.gaeppa.order.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import com.sparta.gaeppa.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_order_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderProducts extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private UUID orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "order_id")
    private Orders order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

//    @Column(name = "product_id")
//    private UUID productId;

    @Column(nullable = false, name = "order_product_name")
    private String orderProductName;

    @Column(nullable = false, name = "order_product_price")
    private int orderProductPrice;

    @Column(nullable = false, name = "order_product_quantity")
    private int orderProductQuantity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id")
    @OrderColumn(name = "order_product_option_idx")
    private List<OrderOptions> orderOptionsList = new ArrayList<>();


}
