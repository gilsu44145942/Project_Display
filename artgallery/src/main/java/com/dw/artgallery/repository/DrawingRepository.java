package com.dw.artgallery.repository;

import com.dw.artgallery.model.Drawing;
import com.dw.artgallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DrawingRepository extends JpaRepository<Drawing, Long> {
    List<Drawing> findByUserAndIsDeletedFalse(User user);


}