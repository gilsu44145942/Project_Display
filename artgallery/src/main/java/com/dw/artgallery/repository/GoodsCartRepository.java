package com.dw.artgallery.repository;

import com.dw.artgallery.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsCartRepository extends JpaRepository<Goods,Long> {
}
