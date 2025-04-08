package com.dw.artgallery.repository;

import com.dw.artgallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String > {

        Optional<User> findByRealName(String realName);
        List<User> findAllByOrderByEnrolmentDateDesc();
        List<User> findAllByOrderByPointDesc();

}
