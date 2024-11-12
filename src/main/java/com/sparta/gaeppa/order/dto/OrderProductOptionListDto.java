package com.sparta.gaeppa.order.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductOptionListDto {

    private String optionName;
    private int optionPrice;


}
