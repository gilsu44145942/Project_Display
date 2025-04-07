package com.dw.artgallery.repository;

import com.dw.artgallery.model.Biography;
import org.hibernate.boot.jaxb.mapping.JaxbPrePersist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiographyRepository extends JpaRepository<Biography,Long> {
}
