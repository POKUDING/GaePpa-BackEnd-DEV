package com.sparta.gaeppa.order.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import com.sparta.gaeppa.order.dto.OrderProductDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;

@Entity
@Table(name = "p_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    @Column(nullable = false, name = "member_id")
    private UUID memberId;

//    @OneToOne
//    @Column(nullable = false, name = "store_id")
//    private Store store;

    @Column(nullable = false, name = "store_id")
    private UUID storeId;

    @Embedded
    private Address address;

    @Column(nullable = false, name = "order_status")
    private String orderStatus;

    @Column(nullable = false, name = "order_type")
    private String orderType;

    @Column(nullable = false, name = "order_total_price")
    private int orderTotalPrice;

    @Column(name = "order_request")
    private String orderRequest;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @OrderColumn(name = "order_product_idx")
    private List<OrderProducts> orderProductsList = new ArrayList<>();


}
