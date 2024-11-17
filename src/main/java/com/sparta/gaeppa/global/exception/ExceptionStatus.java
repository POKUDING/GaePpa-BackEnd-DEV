package com.sparta.gaeppa.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

    //ProductCategory
    PRODUCT_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "PC001", "상품 카테고리가 존재하지 않습니다."),
    PRODUCT_CATEGORY_HAS_PRODUCTS(HttpStatus.BAD_REQUEST, "PC002", "상품 카테고리에 상품이 존재합니다."),

    //Products
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "상품이 존재하지 않습니다."),
    PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT, "P002", "상품이 이미 존재합니다."),

    //ProductOptionCategory
    PRODUCT_OPTION_CATEGORY_HAS_OPTIONS(HttpStatus.BAD_REQUEST, "POC001", "상품 옵션 카테고리에 옵션이 존재합니다."),
    PRODUCT_OPTION_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "POC002", "상품 옵션 카테고리가 존재하지 않습니다."),
    PRODUCT_OPTION_CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "POC003", "상품 옵션 카테고리가 이미 존재합니다."),

    //ProductOption
    PRODUCT_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "PO001", "상품 옵션이 존재하지 않습니다."),

    //Users

    //Orders
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "o001", "주문이 존재하지 않습니다."),
    ORDER_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "o002", "잘못된 주문 요청 입니다."),
    ORDER_MODIFICATION_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "o003", "주문 시간이 5분이 경과되어 수정이 불가능합니다."),

    //Payments
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PAY001", "결제내역이 존재하지 않습니다."),
    PAYMENT_MODIFICATION_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "PAY002", "취소된 주문은 결제 추가가 불가능합니다."),

    // JWT
    INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "j001", "유효하지 않은 리프레시 토큰입니다."),

    // ROLE
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "r001", "권한이 없습니다."), //Common

    // AI
    AI_PROMPT_NOT_FOUND(HttpStatus.NOT_FOUND, "a001", "AI 프롬프트가 존재하지 않습니다."),

    //Common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "s001", "서버 에러입니다.");

    private final int status;
    private final String customCode;
    private final String message;
    private final String err;

    ExceptionStatus(HttpStatus httpStatus, String customCode, String message) {
        this.status = httpStatus.value();
        this.customCode = customCode;
        this.message = message;
        this.err = httpStatus.getReasonPhrase();
    }
}
