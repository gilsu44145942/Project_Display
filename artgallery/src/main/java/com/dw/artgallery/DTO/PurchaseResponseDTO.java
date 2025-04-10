package com.dw.artgallery.DTO;

import com.dw.artgallery.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {
    private Long purchaseId;
    private int totalPrice;
    private LocalDate purchaseDate;
    private List<PurchaseGoodsDTO> goods;

    public static PurchaseResponseDTO fromEntity(Purchase purchase){
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        purchaseResponseDTO.setPurchaseId(purchase.getId());
        purchaseResponseDTO.setTotalPrice(purchase.getTotalPrice());
        purchaseResponseDTO.setPurchaseDate(purchase.getPurchaseDate());

        List<PurchaseGoodsDTO> goodsDTOList = purchase.getPurchaseGoodsList()
                .stream().map(PurchaseGoodsDTO::fromEntity)
                .toList();

        purchaseResponseDTO.setGoods(goodsDTOList);
        return purchaseResponseDTO;
    }
}
