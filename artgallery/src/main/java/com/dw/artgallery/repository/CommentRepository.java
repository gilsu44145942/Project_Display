package com.dw.artgallery.repository;

import com.dw.artgallery.model.Comment;

import com.dw.artgallery.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByCommunityAndIsDeletedFalse(Community community);

}
