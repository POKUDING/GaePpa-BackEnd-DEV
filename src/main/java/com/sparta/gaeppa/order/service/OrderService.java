package com.sparta.gaeppa.order.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.dto.OrderResponseListDto;
import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;

    public OrderResponseListDto getAllOrdersByMemberId(UUID memberId) {

        List<Orders> orderlist = orderRepository.findAllOrdersByMemberId(memberId);

        if(orderlist.isEmpty()){
            throw new ServiceException(ExceptionStatus.ORDER_NOT_FOUND);
        }

        return new OrderResponseListDto(orderlist);
    }
}
