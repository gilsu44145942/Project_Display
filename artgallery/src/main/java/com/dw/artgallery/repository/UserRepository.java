package com.dw.artgallery.repository;

import com.dw.artgallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String > {
}
