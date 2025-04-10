package com.dw.artgallery.repository;

import com.dw.artgallery.model.Drawing;
import com.dw.artgallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DrawingRepository extends JpaRepository<Drawing, Long> {
    @Query("SELECT d FROM Drawing d WHERE d.user = :user AND d.isDeleted = false")
    List<Drawing> findByUserNotDeleted(@Param("user") User user);


}