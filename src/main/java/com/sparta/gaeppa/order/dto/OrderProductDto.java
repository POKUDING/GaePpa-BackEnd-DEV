package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.OrderProduct;
import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.product.entity.Product;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderProductDto {

    private Product product;
    private String productName;
    private int productQuantity;
    private int productPrice;
    private List<OrderProductOptionDto> productOptionList;

    public static OrderProductDto from(OrderProduct orderProduct) {
        return OrderProductDto.builder()
                .productName(orderProduct.getOrderProductName())
                .productQuantity(orderProduct.getOrderProductQuantity())
                .productPrice(orderProduct.getOrderProductPrice())
                .build();
    }

    public OrderProduct toEntity(Orders orders) {
        return OrderProduct.builder()
                .product(product)
                .order(orders)
                .orderProductName(productName)
                .orderProductQuantity(productQuantity)
                .orderProductPrice(productPrice)
                .build();
    }
}
