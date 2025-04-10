package com.dw.artgallery.service;

import com.dw.artgallery.DTO.GoodsCartDTO;
import com.dw.artgallery.DTO.PurchaseGoodsDTO;
import com.dw.artgallery.DTO.PurchaseResponseDTO;
import com.dw.artgallery.DTO.PurchaseSummaryDTO;
import com.dw.artgallery.model.Goods;
import com.dw.artgallery.model.Purchase;
import com.dw.artgallery.model.PurchaseGoods;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.GoodsRepository;
import com.dw.artgallery.repository.PurchaseGoodsRepository;
import com.dw.artgallery.repository.PurchaseRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.exception.PermissionDeniedException;
import com.dw.exception.ResourceNotFoundException;
import com.dw.exception.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final GoodsRepository goodsRepository;
    private final GoodsCartService goodsCartService;
    private final PurchaseGoodsRepository purchaseGoodsRepository;


    @Transactional
    public PurchaseResponseDTO createPurchase(String userId, List<GoodsCartDTO> cartItems){
        User currentUser = userRepository.findByUserId(userId)
                .orElseThrow(()-> new PermissionDeniedException("존재하지 않는 사용자입니다."));

        Purchase purchase = new Purchase();

        purchase.setUser(currentUser);
        purchase.setPurchaseDate(LocalDate.now());

        List<PurchaseGoods> purchaseGoodsList = new ArrayList<>();
        int totalPrice = 0;

        for (GoodsCartDTO cartItem : cartItems){
            Goods goods = goodsRepository.findById(cartItem.getGoodsId())
                    .orElseThrow(()->new ResourceNotFoundException("존재하지 않는 상품입니다."));

            int quantity = cartItem.getAmount();
            int price = goods.getPrice();

            PurchaseGoods purchaseGoods = new PurchaseGoods();
            purchaseGoods.setPurchase(purchase);
            purchaseGoods.setGoods(goods);
            purchaseGoods.setQuantity(quantity);
            purchaseGoods.setPrice(price);

            purchaseGoodsList.add(purchaseGoods);
            totalPrice += price*quantity;
        }

        purchase.setTotalPrice(totalPrice);
        purchase.setPurchaseGoodsList(purchaseGoodsList);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        List<Long> cartIds = cartItems.stream().map(GoodsCartDTO::getId).toList();
        goodsCartService.deleteGoodsCartByIds(cartIds, userId);

        return PurchaseResponseDTO.fromEntity(savedPurchase);
    }

    public List<PurchaseSummaryDTO> getMyPurchaseHistory(String userId){
        List<PurchaseGoods> purchaseGoodsList = purchaseGoodsRepository.findByPurchase_User_UserId(userId);
        return purchaseGoodsList.stream().map(PurchaseSummaryDTO::fromEntity).toList();
    }

    public List<PurchaseSummaryDTO> getAllPurchaseHistory(){
        List<PurchaseGoods> allPurchaseHistory = purchaseGoodsRepository.findAll();
        return allPurchaseHistory.stream()
                .map(PurchaseSummaryDTO::fromEntity)
                .toList();

    }

    public List<PurchaseSummaryDTO> getPurchaseHistoryByUserId(String userId) {
        List<PurchaseGoods> goodsList = purchaseGoodsRepository.findByPurchase_User_UserId(userId);
        return goodsList.stream()
                .map(PurchaseSummaryDTO::fromEntity)
                .toList();
    }


}
