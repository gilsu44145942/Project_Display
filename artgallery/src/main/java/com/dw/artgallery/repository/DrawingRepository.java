package com.dw.artgallery.repository;

import com.dw.artgallery.model.Drawing;
import org.hibernate.boot.jaxb.mapping.JaxbPrePersist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawingRepository extends JpaRepository<Drawing,Long> {
}
