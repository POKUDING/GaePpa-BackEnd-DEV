package com.sparta.gaeppa.cart.controller;

import com.sparta.gaeppa.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

//    @GetMapping
//    public ResponseEntity<ApiResult<CartListResponseDto>> getCart(@RequestParam("memberId") UUID memberId) {
//
//        CartListResponseDto responseDto = cartService.getAllCartByMemberId(memberId);
//
//        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
//    }
}
