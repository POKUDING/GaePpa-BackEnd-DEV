package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.OrderProduct;
import com.sparta.gaeppa.product.entity.Product;
import java.util.List;
import java.util.UUID;
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

    private UUID productId;
    private int productQuantity;
    private List<OrderProductOptionDto> productOptionList;

    public static OrderProductDto from(OrderProduct orderProduct) {
        return OrderProductDto.builder()
                .productQuantity(orderProduct.getOrderProductQuantity())
                .build();
    }

    public OrderProduct toEntity(Product product) {

        return OrderProduct.builder()
                .orderProductName(product.getName())
                .orderProductPrice(product.getPrice())
                .orderProductQuantity(getProductQuantity())
                .build();

    }

}
