package com.sparta.gaeppa.order.dto;


import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductsListDto {

    private String productName;
    private int quantity;
    private int productTotalPrice;
    private List<OrderProductOptionsListDto> optionList;
}
