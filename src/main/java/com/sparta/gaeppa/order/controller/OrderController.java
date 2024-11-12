package com.sparta.gaeppa.order.controller;

import com.sparta.gaeppa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @GetMapping
//    public ResponseEntity<ApiResult<OrderResponseDto>> getOrders(@RequestParam("memberId") UUID memberId) {
//
//        OrderResponseDto responseDto = orderService.getAllOrdersByMemberId(memberId);
//
//        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
//    }
}
