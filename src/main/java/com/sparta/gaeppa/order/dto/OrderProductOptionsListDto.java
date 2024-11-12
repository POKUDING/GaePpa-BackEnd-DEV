package com.sparta.gaeppa.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductOptionsListDto {

    private String optionName;
    private String optionPrice;

    @Builder
    public OrderProductOptionsListDto(String optionName, String optionPrice) {
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }
}
