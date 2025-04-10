package com.dw.artgallery.controller;


import com.dw.artgallery.DTO.ReviewDTO;
import com.dw.artgallery.model.User;
import com.dw.artgallery.service.ReviewService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    // GoodsId 로 리뷰 조회
    @GetMapping("/goods/{goodsId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByGoodsId(@PathVariable Long goodsId) {
        return new ResponseEntity<>(reviewService.getReviewsByGoodsId(goodsId), HttpStatus.OK);
    }

    // ReviewId 로 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }

    // reviewId로 수정
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long reviewId,
                                                  @RequestBody ReviewDTO dto,
                                                  @AuthenticationPrincipal User user) {
        ReviewDTO updated = reviewService.updateReview(reviewId, dto, user);
        return ResponseEntity.ok(updated);
    }

    // 관리자 혹은 본인이 리뷰 삭제
    @PostMapping("/deleted/{reviewId}")
    public ResponseEntity<String> deletedReview(@PathVariable Long reviewId,
                                                @AuthenticationPrincipal User user) {
        String result = reviewService.deletedReview(reviewId, user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 구매내역의 해당 굿즈상품이 존재하는 회원의 이름으로 리뷰 작성
    @PostMapping("/add/{goodsId}")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable Long goodsId,
                                               @RequestBody CreateReviewRequest request,
                                               @AuthenticationPrincipal User user) {

        return new ResponseEntity<>(reviewService.addReview(goodsId, request.getText(), user),HttpStatus.OK);
    }
    @Data
    public static class CreateReviewRequest {
        private String text;
    }

}