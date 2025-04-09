package com.dw.artgallery.repository;

import com.dw.artgallery.model.Goods;
import com.dw.artgallery.model.GoodsCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsCartRepository extends JpaRepository<GoodsCart,Long> {
}
