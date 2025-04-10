package com.dw.artgallery.service;

import com.dw.artgallery.DTO.ReviewDTO;
import com.dw.artgallery.model.Goods;
import com.dw.artgallery.model.Review;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.GoodsRepository;
import com.dw.artgallery.repository.ReviewRepository;
import com.dw.exception.InvalidRequestException;
import com.dw.exception.ResourceNotFoundException;
import com.dw.exception.UnauthorizedUserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final GoodsRepository goodsRepository;

    public List<ReviewDTO> getAllReviews() {
        try {
            return reviewRepository.findAll().stream().map(Review::toDTO).toList();
        } catch (InvalidRequestException e) {
            throw new InvalidRequestException("정상적인 요청이 아닙니다");
        }
    }

    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 리뷰 ID입니다"));
        return review.toDTO();
    }

    public List<ReviewDTO> getReviewsByProductId(String goodsId) {
        try {
            return reviewRepository.findByGoodsId(goodsId).stream()
                    .map(Review::toDTO)
                    .toList();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("존재하지 않은 제품번호입니다");
        }
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        User user = userService.findByUsername(reviewDTO.getUsername());
        Goods goods = goodsRepository.findById(reviewDTO.getGoodsId())
                .orElseThrow(() -> new ResourceNotFoundException("해당 상품을 찾을 수 없습니다."));

        Review review = new Review();
        review.setText(reviewDTO.getText());
        review.setUser(user);
        review.setGoods(goods);
        review.setCreatedAt(LocalDate.now());

        Review savedReview = reviewRepository.save(review);
        return savedReview.toDTO();
    }

    public String deleteReview(Long reviewId) {
        User currentUser = userService.getCurrentUser();
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않은 리뷰입니다."));

        if (review.getUser().equals(currentUser)) {
            review.setIsActive(false);
            reviewRepository.save(review);
            return "리뷰가 정상 삭제되었습니다.";
        }

        if (currentUser.getAuthority().getAuthorityName().equals("ADMIN")) {
            review.setIsActive(false);
            reviewRepository.save(review);
            return "관리자가 해당 리뷰를 삭제하였습니다.";
        }

        throw new UnauthorizedUserException("리뷰를 삭제할 권한이 없습니다.");
    }
}