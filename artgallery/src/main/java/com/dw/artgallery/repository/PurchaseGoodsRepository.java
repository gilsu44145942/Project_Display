package com.dw.artgallery.repository;

import com.dw.artgallery.model.PurchaseGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseGoodsRepository extends JpaRepository<PurchaseGoods, Long> {
    List<PurchaseGoods> findByPurchase_User_UserId(String userId);
}
