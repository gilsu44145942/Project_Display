package com.dw.artgallery.repository;

import com.dw.artgallery.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByEmail(String email);
    List<Contact> findByTitleContaining(String title);
    List<Contact> findByUser_UserId(String userId);
}