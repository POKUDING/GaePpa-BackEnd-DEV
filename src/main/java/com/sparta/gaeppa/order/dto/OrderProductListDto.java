package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.OrderProduct;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductListDto {

    private List<OrderProductDto> products;

    public OrderProductListDto(List<OrderProduct> products) {
        this.products = products.stream().map(OrderProductDto::from).toList();
    }

}
