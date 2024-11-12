package com.sparta.gaeppa.order.service;

import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.order.repository.OrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;

    public OrderResponseDto getAllOrdersByMemberId(UUID memberId) {

        Orders orders = orderRepository.findAllOrdersByMemberId(memberId);

//        return OrderResponseDto(orders);
        return null;
    }
}
