package com.dw.artgallery.repository;

import com.dw.artgallery.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
