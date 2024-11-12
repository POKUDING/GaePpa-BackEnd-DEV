package com.sparta.gaeppa.cart.service;

import com.sparta.gaeppa.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private CartRepository cartRepository;

//    public CartListResponseDto getAllCartByMemberId(UUID memberId) {
//
//        // 쿠키에 저장된 장바구니가 있는지 확인
//
//        // 없다면 비어있음 메세지 전달
//
//        // 있다면 장바구니 정보 전달
//        CartListResponseDto responseDto;
//
//        return;
//    }
}
