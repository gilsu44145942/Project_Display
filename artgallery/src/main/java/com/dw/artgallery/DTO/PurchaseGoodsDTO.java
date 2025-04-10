package com.dw.artgallery.DTO;

import com.dw.artgallery.model.PurchaseGoods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PurchaseGoodsDTO {
    private String goodsName;
    private String thumbnailUrl;
    private int quantity;
    private int price;

    public static PurchaseGoodsDTO fromEntity(PurchaseGoods purchaseGoods){
        PurchaseGoodsDTO purchaseGoodsDTO = new PurchaseGoodsDTO();

        purchaseGoodsDTO.setGoodsName(purchaseGoods.getGoods().getName());
        purchaseGoodsDTO.setQuantity(purchaseGoods.getQuantity());
        purchaseGoodsDTO.setPrice(purchaseGoods.getPrice());
        purchaseGoodsDTO.setThumbnailUrl(purchaseGoods.getGoods().getThumbnailUrl());

        return purchaseGoodsDTO;

    }
}
