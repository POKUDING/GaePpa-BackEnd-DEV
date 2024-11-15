package com.sparta.gaeppa.order.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.order.dto.OrderListResponseDto;
import com.sparta.gaeppa.order.dto.OrderProductDto;
import com.sparta.gaeppa.order.dto.OrderProductOptionDto;
import com.sparta.gaeppa.order.dto.OrderRequestDto;
import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.entity.OrderOption;
import com.sparta.gaeppa.order.entity.OrderProduct;
import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.order.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;


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
        List<OrderProductDto> productListDto = requestDto.getOrderProductList();

        for (OrderProductDto productDto : productListDto) {

            try {
                OrderProduct product = productDto.toEntity(orders);
                orders.putOrderProduct(product);

                List<OrderProductOptionDto> optionListDto = productDto.getProductOptionList();
                for (OrderProductOptionDto optionDto : optionListDto) {
                    OrderOption option = optionDto.toEntity(product);
                    product.putOrderOption(option);
                }


            } catch (NullPointerException e) {
                throw new ServiceException(ExceptionStatus.ORDER_REQUEST_NOT_FOUND);
            }
        }

        return OrderResponseDto.from(orderRepository.save(orders));
    }

}
