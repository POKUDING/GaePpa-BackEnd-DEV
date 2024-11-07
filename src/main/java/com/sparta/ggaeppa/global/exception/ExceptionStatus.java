package com.sparta.ggaeppa.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

    //Products
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "Product not found"), PRODUCT_ALREADY_EXISTS(HttpStatus.CONFLICT,
            "P002", "Product already exists"), PRODUCT_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "P003",
            "Product option not found"), PRODUCT_OPTION_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "P004",
            "Product option category not found"), PRODUCT_OPTION_CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "P005",
            "Product option category already exists"),

    //Users

    //Orders

    // JWT
    INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "j001", "유효하지 않은 리프레시 토큰입니다."),

    // ROLE
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "r001", "권한이 없습니다."), //Common
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
