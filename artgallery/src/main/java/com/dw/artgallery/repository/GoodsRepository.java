package com.dw.artgallery.repository;

import com.dw.artgallery.model.GoodsCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsCart,Long> {
}
