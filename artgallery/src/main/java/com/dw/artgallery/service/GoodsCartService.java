package com.dw.artgallery.service;

import com.dw.artgallery.DTO.GoodsCartDTO;
import com.dw.artgallery.model.Goods;
import com.dw.artgallery.model.GoodsCart;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.GoodsCartRepository;
import com.dw.artgallery.repository.GoodsRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.artgallery.exception.ResourceNotFoundException;
import com.dw.artgallery.exception.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsCartService {
    private final GoodsCartRepository goodsCartRepository;
    private final UserRepository userRepository;
    private final GoodsRepository goodsRepository;

    public List<GoodsCartDTO>getAllGoodsCart(){
        return goodsCartRepository.findAll().stream().map(GoodsCartDTO::fromEntity).toList();
    }

    public List<GoodsCartDTO> getGoodsCartByUser(String userId){
        return goodsCartRepository.findByUser_UserId(userId).stream().map(GoodsCartDTO::fromEntity).toList();
    }

    public GoodsCartDTO addGoodsCart(GoodsCartDTO goodsCartDTO){
        User user = userRepository.findByUserId(goodsCartDTO.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("해당 유저가 존재하지 않습니다"));

        Goods goods = goodsRepository.findById(goodsCartDTO.getGoodsId())
                .orElseThrow(()-> new ResourceNotFoundException("해당 제품이 존재하지 않습니다."));

        GoodsCart goodsCart = new GoodsCart();
        goodsCart.setSum(goodsCartDTO.getSum());
        goodsCart.setAmount(goodsCartDTO.getAmount());
        goodsCart.setGoods(goods);
        goodsCart.setUser(user);

        GoodsCart savedGoodsCart = goodsCartRepository.save(goodsCart);
        return GoodsCartDTO.fromEntity(savedGoodsCart);
    }

    public String deleteGoodsCartByIds(List<Long> ids, String currentUserId) {
        int deletedCount = 0;

        for (Long id : ids) {
            GoodsCart cart = goodsCartRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("장바구니 항목 없음: " + id));

            if (!cart.getUser().getUserId().equals(currentUserId)) {
                throw new UnauthorizedUserException("다른 사람의 장바구니는 삭제할 수 없습니다.");
            }
            goodsCartRepository.delete(cart);
            deletedCount++;
        }

        return "장바구니에 담긴 상품 " + deletedCount + "개를 정상적으로 삭제하였습니다.";
    }



}