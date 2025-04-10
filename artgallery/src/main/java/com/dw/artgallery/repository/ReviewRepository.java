package com.dw.artgallery.repository;

import com.dw.artgallery.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByGoodsId(Long goodsId);
    @Query("SELECT r FROM Review r WHERE r.goods.id = :goodsId AND r.isDeleted = false")
    List<Review> findActiveReviewsByGoodsId(@Param("goodsId") Long goodsId);

}
