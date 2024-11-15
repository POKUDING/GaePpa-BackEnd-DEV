package com.sparta.gaeppa.order.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.order.dto.OrderListResponseDto;
import com.sparta.gaeppa.order.dto.OrderProductDto;
import com.sparta.gaeppa.order.dto.OrderProductOptionDto;
import com.sparta.gaeppa.order.dto.OrderRequestDto;
import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.entity.OrderProduct;
import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.order.repository.OrderRepository;
import com.sparta.gaeppa.product.entity.Product;
import com.sparta.gaeppa.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {


    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Transactional(readOnly = true)
    public OrderListResponseDto getAllOrdersByMemberId(UUID memberId) {

        List<Orders> orderList = orderRepository.findAllOrdersByMemberId(memberId);

        return Optional.of(orderList)
                .filter(list -> !list.isEmpty())
                .map(OrderListResponseDto::new)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.ORDER_NOT_FOUND));
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {

        Orders orders = requestDto.toEntity();
        int orderTotalPrice = 0;

        for (OrderProductDto orderProductDto : requestDto.getOrderProductList()) {

            try {

                Product product = productRepository.findById(orderProductDto.getProductId())
                        .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_NOT_FOUND));

                OrderProduct orderProduct = orderProductDto.toEntity(orders, product);

                if (!orderProduct.getOrderOptionList().isEmpty()) {

                    for (OrderProductOptionDto optionDto : orderProductDto.getProductOptionList()) {

                        orderProduct.putOrderOption(optionDto.toEntity());
                        orderTotalPrice += optionDto.getOptionPrice();
                    }
                }

                log.info("orderProductId >>>>>>" + orderProduct.getOrderProductId());

                orders.putOrderProduct(orderProduct);
                orderTotalPrice += orderProduct.getOrderProductPrice() * orderProduct.getOrderProductQuantity();

            } catch (NullPointerException e) {
                throw new ServiceException(ExceptionStatus.ORDER_REQUEST_NOT_FOUND);
            }
        }
        orders.putOrderTotalPrice(orderTotalPrice);

        return OrderResponseDto.from(orderRepository.save(orders));
    }

//    public void updateOrder(UUID orderId, OrderRequestDto requestDto) {
//
//        Orders orders = orderRepository.findById(orderId)
//                .orElseThrow(() -> new ServiceException(ExceptionStatus.ORDER_NOT_FOUND));
//
//        if (!isWithinFiveMinutes(orders)) {
//            throw new ServiceException(ExceptionStatus.ORDER_MODIFICATION_NOT_ALLOWED);
//        }
//
//        orders.update(requestDto.getStoreId(), requestDto.toEntity().getAddress(), requestDto.getOrderRequest(),
//                requestDto.toEntity().getOrderProductList());
//        int orderTotalPrice = orders.getOrderTotalPrice();
//
//
//    }
//
//    private boolean isWithinFiveMinutes(Orders order) {
//
//        LocalDateTime now = LocalDateTime.now();
//        Duration duration = Duration.between(order.getCreatedAt(), now);
//
//        return duration.toMinutes() < 5;
//    }
}
