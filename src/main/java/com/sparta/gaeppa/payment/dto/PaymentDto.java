package com.sparta.gaeppa.payment.dto;

import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.payment.entity.Payments;
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
public class PaymentDto {

    private UUID orderId;
    private String payStatus;
    private String payType;
    private String payTransactionCode;

    public static PaymentDto from(Payments payments) {
        return PaymentDto.builder()
                .orderId(payments.getOrder().getOrderId())
                .payStatus(payments.getPayStatus())
                .payType(payments.getPayType())
                .payTransactionCode(payments.getPayTransactionCode())
                .build();
    }

    public Payments toEntity(Orders orders) {
        return Payments.builder()
                .order(orders)
                .payStatus(payStatus)
                .payType(payType)
                .payTransactionCode(payTransactionCode)
                .build();
    }

}
