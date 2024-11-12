package com.sparta.gaeppa.order.dto;


import com.sparta.gaeppa.order.entity.OrderProducts;
import com.sparta.gaeppa.order.entity.Orders;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseDto {

    private UUID storeId;
//    private String storeName;
    private AddressDto address;
    private String orderStatus;
    private String orderType;
    private int totalPrice;
    private String orderRequest;
    private List<OrderProducts> orderProductList;

    @Builder
    private OrderResponseDto(UUID storeId, AddressDto address, String orderStatus, String orderType, int totalPrice, String orderRequest, List<OrderProducts> orderProductList) {
        this.storeId = storeId;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.totalPrice = totalPrice;
        this.orderRequest = orderRequest;
        this.orderProductList = orderProductList;
    }

    public static OrderResponseDto from(Orders orders) {
        return OrderResponseDto.builder()
                .storeId(orders.getStoreId())
                .address(AddressDto.from(orders.getAddress()))
                .orderStatus(orders.getOrderStatus())
                .orderType(orders.getOrderType())
                .totalPrice(orders.getOrderTotalPrice())
                .orderRequest(orders.getOrderRequest())
                .orderProductList(orders.getOrderProductsList())
                .build();
    }
}
