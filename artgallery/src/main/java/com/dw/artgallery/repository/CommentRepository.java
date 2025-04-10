package com.dw.artgallery.repository;

import com.dw.artgallery.model.Comment;


import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<Comment,Long> {


}
