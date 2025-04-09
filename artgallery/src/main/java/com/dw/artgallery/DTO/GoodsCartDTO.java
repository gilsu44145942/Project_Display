package com.dw.artgallery.DTO;

import com.dw.artgallery.model.GoodsCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodsCartDTO {

    private Long id;
    private int amount;
    private double sum;
    private String goodsName;
    private Long goodsId;
    private double goodsPrice;
    private String imgUrl;
    private String userId;
    private String nickName;

    public static GoodsCartDTO fromEntity(GoodsCart goodsCart){
        return new GoodsCartDTO(
                goodsCart.getId(),
                goodsCart.getAmount(),
                goodsCart.getSum(),
                goodsCart.getGoods().getName(),
                goodsCart.getGoods().getId(),
                goodsCart.getGoods().getPrice(),
                goodsCart.getGoods().getThumbnailUrl(),
                goodsCart.getUser().getUserId(),
                goodsCart.getUser().getNickName()
        );
    }
}
