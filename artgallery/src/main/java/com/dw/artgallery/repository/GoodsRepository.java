package com.dw.artgallery.repository;

import com.dw.artgallery.DTO.GoodsDTO;
import com.dw.artgallery.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
    public List<Goods> findByNameLike(String name);


    public List<Goods> findAllByOrderByPriceAsc();

    public List<Goods> findAllByOrderByPriceDesc();
}
