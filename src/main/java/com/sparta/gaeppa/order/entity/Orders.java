package com.sparta.gaeppa.order.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

//    Store API 추가 후 수정
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false, name = "store_id")
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

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProductList = new ArrayList<>();

//    Store API 추가 후 수정
//    @Builder
//    private Orders(UUID memberId, Store store, Address address, int orderTotalPrice, String orderType,
//                   String orderRequest, List<OrderProduct> orderProductList) {
//        this.memberId = memberId;
//        this.store = store;
//        this.address = address;
//        this.orderStatus = "주문대기";
//        this.orderType = orderType;
//        this.orderTotalPrice = orderTotalPrice;
//        this.orderRequest = orderRequest;
//        this.orderProductList = orderProductList;
//    }

    // Store 추가 후 삭제
    @Builder
    private Orders(UUID memberId, UUID storeId, Address address, String orderType, int orderTotalPrice,
                   String orderRequest) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.address = address;
        this.orderStatus = "주문대기";
        this.orderType = orderType;
        this.orderTotalPrice = orderTotalPrice;
        this.orderRequest = orderRequest;
    }

    public void putOrderProduct(OrderProduct orderProduct) {

        if (!orderProductList.contains(orderProduct)) {
            orderProductList.add(orderProduct);
        }

    }

    public void putOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void update(UUID storeId, Address address, String orderRequest,
                       List<OrderProduct> orderProductList) {
        this.storeId = storeId;
        this.address = address;
        this.orderTotalPrice = 0;
        this.orderRequest = orderRequest;
        this.orderProductList = orderProductList;
    }
}
