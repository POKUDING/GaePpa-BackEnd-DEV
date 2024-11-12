package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.Orders;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseListDto {

    private List<OrderResponseDto> orders;

    public OrderResponseListDto(List<Orders> orders) {
        this.orders = orders.stream().map(OrderResponseDto::from).toList();
    }

}
