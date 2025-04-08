package com.dw.artgallery.repository;

import com.dw.artgallery.model.ArtistGallery;
import com.dw.artgallery.model.UserGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserGalleryRepository extends JpaRepository<UserGallery,Long> {
    List<UserGallery> findByTitleLike(String title);

    @Query("SELECT u FROM UserGallery u WHERE u.startDate <= :today AND u.endDate >= :today")
    List<UserGallery> findNowGallery(@Param("today") LocalDate today);

    @Query("SELECT u FROM UserGallery u WHERE u.endDate < :today")
    List<UserGallery> findPastGallery(@Param("today") LocalDate today);

    @Query("SELECT u FROM UserGallery u WHERE u.startDate > :today")
    List<UserGallery> findExpectedGallery(@Param("today") LocalDate today);
}
