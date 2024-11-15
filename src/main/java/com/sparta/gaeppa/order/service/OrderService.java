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
import java.time.Duration;
import java.time.LocalDateTime;
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

        for (OrderProductDto orderProductDto : requestDto.getOrderProductList()) {

            try {

                Product product = productRepository.findById(orderProductDto.getProductId())
                        .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_NOT_FOUND));

                OrderProduct orderProduct = orderProductDto.toEntity(orders, product);

                orderProductDto.getProductOptionList().stream()
                        .map(OrderProductOptionDto::toEntity)
                        .forEach(orderProduct::putOrderOption);

                log.info("[OrderService] orderProductId >>>>>>>> {}", orderProduct.getOrderProductId());

                orders.putOrderProduct(orderProduct);

            } catch (NullPointerException e) {
                throw new ServiceException(ExceptionStatus.ORDER_REQUEST_NOT_FOUND);
            }
        }
        orders.calOrderTotalPrice();
        return OrderResponseDto.from(orderRepository.save(orders));
    }

    @Transactional
    public void cancelOrder(UUID orderId) {

        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.ORDER_NOT_FOUND));

        if (!isWithinFiveMinutes(orders)) {
            throw new ServiceException(ExceptionStatus.ORDER_MODIFICATION_NOT_ALLOWED);
        }

        orders.cancel(orderId);
    }

    private boolean isWithinFiveMinutes(Orders order) {

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(order.getCreatedAt(), now);

        return duration.toMinutes() < 5;
    }


}
