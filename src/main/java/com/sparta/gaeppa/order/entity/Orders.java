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


    @Builder
    private Orders(UUID memberId, UUID storeId, Address address, String orderType,
                   String orderRequest) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.address = address;
        this.orderStatus = "주문완료";
        this.orderType = orderType;
        this.orderRequest = orderRequest;
    }

    public void putOrderProduct(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
    }

    public void calOrderTotalPrice() {
        int price = 0;

        for (OrderProduct orderProduct : orderProductList) {
            for (OrderOption orderOption : orderProduct.getOrderOptionList()) {
                orderTotalPrice += orderOption.getOptionPrice();
            }
            price += orderProduct.getOrderProductPrice() * orderProduct.getOrderProductQuantity();
        }
        this.orderTotalPrice = price;

    }

    public void cancel(UUID orderId) {
        this.orderStatus = "주문취소";
    }
}
