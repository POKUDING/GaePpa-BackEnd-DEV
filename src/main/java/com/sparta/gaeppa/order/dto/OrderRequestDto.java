package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.product.dto.product.ProductRequestDto;
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
public class OrderRequestDto {

    private UUID memberId;
    //    Store API 추가 후 수정
//    private StoreRequestDto store;
    private UUID storeId;
    private ProductRequestDto product;
    private AddressDto address;
    private String orderType;
    private int totalPrice;
    private String orderRequest;
    private List<OrderProductDto> orderProductList;


    public Orders toEntity() {
        return Orders.builder()
                .memberId(this.memberId)
                .storeId(this.storeId)
                .address(AddressDto.toEntity(this.address))
                .orderType(this.orderType)
                .orderRequest(this.orderRequest)
                .orderTotalPrice(totalPrice)
                .build();
    }
}
