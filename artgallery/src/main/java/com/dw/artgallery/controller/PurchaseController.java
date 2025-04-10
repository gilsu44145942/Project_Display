package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.GoodsCartDTO;
import com.dw.artgallery.DTO.PurchaseResponseDTO;
import com.dw.artgallery.DTO.PurchaseSummaryDTO;
import com.dw.artgallery.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> createPurchase(@RequestBody List<GoodsCartDTO> cartItems, Authentication authentication){
        String userId = authentication.getName();
        PurchaseResponseDTO purchaseResponseDTO = purchaseService.createPurchase(userId, cartItems);
        return new ResponseEntity<>(purchaseResponseDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<PurchaseSummaryDTO>> getMyPurchaseHistory(Authentication authentication){
        String userId = authentication.getName();
        return new ResponseEntity<>(purchaseService.getMyPurchaseHistory(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<PurchaseSummaryDTO>> getAllPurchaseHistory(){
        return new ResponseEntity<>(purchaseService.getAllPurchaseHistory(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseSummaryDTO>> getPurchaseHistoryByUserId(@PathVariable String userId){
        return new ResponseEntity<>(purchaseService.getPurchaseHistoryByUserId(userId), HttpStatus.OK);
    }
}
