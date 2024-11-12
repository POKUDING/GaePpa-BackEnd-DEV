package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.Address;
import com.sparta.gaeppa.order.entity.Orders;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseDto {

    private UUID storeId;
    private UUID memberId;
    private int totalPrice;
    private List<OrderProductsListDto> productList;
    private Address address;
    private String orderStatus;
    private String orderType;
    private String orderRequest;

    @Builder
    public OrderResponseDto(UUID storeId, UUID memberId, int totalPrice, List<OrderProductsListDto> productList,
                            Address address, String orderStatus, String orderType, String orderRequest) {
        this.storeId = storeId;
        this.memberId = memberId;
        this.totalPrice = totalPrice;
        this.productList = productList;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderRequest = orderRequest;
    }

    public static OrderResponseDto from(Orders orders) {
        return OrderResponseDto.builder()
                .storeId(orders.getStoreId())
                .memberId(orders.getMemberId())
                .storeId(orders.getStoreId())
                .address(orders.getAddress())
                .orderStatus(orders.getOrderStatus())
                .orderType(orders.getOrderType())
                .totalPrice(orders.getOrderTotalPrice())
                .build();
    }
}
