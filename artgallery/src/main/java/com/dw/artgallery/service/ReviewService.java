package com.dw.artgallery.service;


import com.dw.artgallery.DTO.ReviewDTO;
import com.dw.artgallery.model.Goods;
import com.dw.artgallery.model.Review;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.GoodsRepository;
import com.dw.artgallery.repository.PurchaseGoodsRepository;
import com.dw.artgallery.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PurchaseGoodsRepository purchaseGoodsRepository;
    @Autowired
    GoodsRepository goodsRepository;

    public List<ReviewDTO> getReviewsByGoodsId(Long goodsId) {
        return reviewRepository.findActiveReviewsByGoodsId(goodsId)
                .stream()
                .map(Review::toDto)
                .toList();
    }

    public ReviewDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("리뷰를 찾을 수 없습니다. ID: " + reviewId));
        return review.toDto();
    }

    public ReviewDTO updateReview(Long reviewId, ReviewDTO dto, User user) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("리뷰를 찾을 수 없습니다."));
        if (!review.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("본인이 작성한 리뷰만 수정할 수 있습니다.");
        }
        review.setText(dto.getText());
        review.setCreatedAt(LocalDate.now());
        reviewRepository.save(review);

        return review.toDto();
    }

    public String deletedReview(Long reviewId, User currentUser) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰를 찾을 수 없습니다."));

        boolean isAuthor = review.getUser().getUserId().equals(currentUser.getUserId());
        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAuthor && !isAdmin) {
            throw new SecurityException("본인 또는 관리자만 삭제할 수 있습니다.");
        }
        review.setIsDeleted(true);
        reviewRepository.save(review);

        return "리뷰가 성공적으로 삭제되었습니다.";
    }

    public ReviewDTO addReview(Long goodsId, String text, User user) {

        boolean hasPurchased = purchaseGoodsRepository.hasUserPurchasedGoods(goodsId, user.getUserId());
        if (!hasPurchased) {
            throw new IllegalStateException("이 상품을 구매한 사용자만 리뷰를 작성할 수 있습니다.");
        }

        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        Review review = new Review();
        review.setText(text);
        review.setUser(user);
        review.setGoods(goods);
        review.setCreatedAt(LocalDate.now());
        review.setIsDeleted(false);

        reviewRepository.save(review);

        return review.toDto();
    }


}