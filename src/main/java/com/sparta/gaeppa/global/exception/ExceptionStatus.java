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
    ORDER_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "o001", "주문이 존재하지 않습니다."),

    // JWT
    INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "j001", "유효하지 않은 리프레시 토큰입니다."),

    // ROLE
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "r001", "권한이 없습니다."), //Common

    // AI
    AI_PROMPT_NOT_FOUND(HttpStatus.NOT_FOUND, "a001", "AI 프롬프트가 존재하지 않습니다."),

    //Storage
    FILE_UPLOAD_LOCATION_NOT_CONFIGURED(HttpStatus.INTERNAL_SERVER_ERROR, "s001", "파일 업로드 위치가 설정되지 않았습니다."),
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "s002", "빈 파일입니다."),
    OUTSIDE_CURRENT_DIRECTORY(HttpStatus.BAD_REQUEST, "s003", "현재 디렉토리 밖에 있는 파일입니다."),
    FILE_STORAGE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "s004", "파일 저장에 실패했습니다."),
    READ_FILE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "s005", "파일 읽기에 실패했습니다."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "s006", "파일이 존재하지 않습니다."),
    STORAGE_SERVICE_INIT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "s007", "파일 저장 서비스 초기화에 실패했습니다."),

    //Image
    PRODUCT_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "i001", "상품 이미지가 존재하지 않습니다."),
    NOT_SUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "i002", "지원하지 않는 확장자입니다."),

    //Common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "s001", "서버 에러입니다."),

    // Authentication
    AUTHENTICATION_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "a001", "틀린 비밀번호를 입력했습니다."),
    AUTHENTICATION_NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "a002", "존재하지 않는 이메일 입니다."),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "a003", "인증에 실패했습니다."),
    DUPLICATE_MEMBER(HttpStatus.UNAUTHORIZED, "a004", "중복되는 회원입니다."),
    EXISTING_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, "a005", "해당 이메일에 여러 계정이 존재합니다."),
    // Authentication 관련 예외 추가
    AUTHENTICATION_EMAIL_NOT_VERIFIED(HttpStatus.UNAUTHORIZED, "a006", "이메일 인증이 완료되지 않았습니다."),
    AUTHENTICATION_INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "a007", "인증에 실패했습니다."),
    AUTHENTICATION_INVALID_CONTENT_TYPE(HttpStatus.BAD_REQUEST, "a008", "지원하지 않는 Content-Type입니다."),
    AUTHENTICATION_JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, "a009", "요청 본문을 파싱할 수 없습니다."),
    AUTHENTICATION_EMAIL_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "a010", "이메일이 입력되지 않았습니다."),
    AUTHENTICATION_PASSWORD_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "a011", "비밀번호가 입력되지 않았습니다.");


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
