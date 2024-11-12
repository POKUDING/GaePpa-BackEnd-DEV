package com.sparta.gaeppa.order.controller;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.dto.OrderResponseListDto;
import com.sparta.gaeppa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResult<OrderResponseListDto>> getOrders(@RequestParam("memberId") UUID memberId) {

        OrderResponseListDto responseDto = orderService.getAllOrdersByMemberId(memberId);

        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
    }
}
