package com.sparta.gaeppa.order.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.order.dto.OrderListResponseDto;
import com.sparta.gaeppa.order.dto.OrderRequestDto;
import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.service.OrderService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResult<OrderListResponseDto>> getAllOrdersByMemberId(
            @RequestParam("memberId") UUID memberId) {

        OrderListResponseDto responseDto = orderService.getAllOrdersByMemberId(memberId);

        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<OrderResponseDto>> createOrder(@RequestBody OrderRequestDto requestDto) {

        OrderResponseDto responseDto = orderService.createOrder(requestDto);

        return new ResponseEntity<>(success(responseDto), HttpStatus.CREATED);
    }


    @PatchMapping
    public ResponseEntity<ApiResult<String>> cancelOrder(@RequestParam("orderId") UUID orderId) {

        orderService.cancelOrder(orderId);

        return new ResponseEntity<>(success("Cancel Order Success"), HttpStatus.OK);
    }
}
