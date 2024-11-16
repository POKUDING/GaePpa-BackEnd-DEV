package com.sparta.gaeppa.global.exception;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.error;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiError;
import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ApiResult<ApiError>> handleControllerException(ControllerException e) {
        log.error("ControllerException: {}", e.getMessage());
        return new ResponseEntity<>(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResult<ApiError>> handleServiceException(ServiceException e) {
        log.error("ServiceException: {}", e.getMessage());
        return new ResponseEntity<>(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ApiResult<ApiError>> handleRepositoryException(RepositoryException e) {
        log.error("RepositoryException: {}", e.getMessage());
        return new ResponseEntity<>(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }

    @ExceptionHandler(AIException.class)
    public ResponseEntity<ApiResult<ApiError>> handleAIException(AIException e) {
        log.error("AIException: {}", e.getMessage());
        return new ResponseEntity<>(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ApiResult<ApiError>> handleStorageException(StorageException e) {
        log.error("StorageException: {}", e.getMessage());
        return new ResponseEntity<>(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResult<ApiError>> handleException(Exception e) {
//        log.error("Exception: {}", e.getMessage());
//        return new ResponseEntity<>(error("서버 오류입니다."), new HttpHeaders(),
//                ExceptionStatus.INTERNAL_SERVER_ERROR.getStatus());
//    }
}
