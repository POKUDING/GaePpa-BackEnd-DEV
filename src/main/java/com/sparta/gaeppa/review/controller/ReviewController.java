package com.sparta.gaeppa.review.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.review.dto.ReviewRequestDto;
import com.sparta.gaeppa.review.dto.ReviewResponseDto;
import com.sparta.gaeppa.review.service.ReviewService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ApiResult<List<ReviewResponseDto>>> getAllReviewsByStoreId(
            @RequestParam("storeid") UUID storeId) {

        List<ReviewResponseDto> responseDto = reviewService.getAllReviewsByStoreId(storeId);

        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResult<ReviewResponseDto>> getReviewsByReviewId(@RequestParam("reviewId") UUID reviewId) {

        ReviewResponseDto responseDto = reviewService.getReviewsByReviewId(reviewId);

        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<ReviewResponseDto>> createReview(@RequestBody ReviewRequestDto requestDto) {

        ReviewResponseDto responseDto = reviewService.createReview(requestDto);

        return new ResponseEntity<>(success(responseDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResult<String>> deleteReview(@PathVariable UUID reviewId, @RequestParam String username) {

        // 삭제 권한 manager 제한 필요
        reviewService.deleteReview(reviewId, username);

        return new ResponseEntity<>(success("Review deleted Success"), HttpStatus.OK);
    }
}
