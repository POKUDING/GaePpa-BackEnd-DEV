package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.OrderOption;
import com.sparta.gaeppa.order.entity.OrderProduct;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderProductOptionDto {

    private OrderProductDto orderProduct;
    private String optionName;
    private int optionPrice;

    public OrderOption toEntity(OrderProduct orderProduct) {
        return OrderOption.builder()
                .orderProduct(orderProduct)
                .optionName(optionName)
                .optionPrice(optionPrice)
                .build();
    }

    public static OrderProductOptionDto from(OrderOption option) {
        return OrderProductOptionDto.builder()
                .orderProduct(OrderProductDto.from(option.getOrderProduct()))
                .optionName(option.getOptionName())
                .optionPrice(option.getOptionPrice())
                .build();
    }
}
