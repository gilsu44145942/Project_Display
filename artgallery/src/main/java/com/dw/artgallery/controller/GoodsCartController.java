package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.GoodsCartDTO;
import com.dw.artgallery.service.GoodsCartService;
import com.dw.artgallery.service.GoodsService;
import com.dw.artgallery.exception.UnauthorizedUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class GoodsCartController {
    private final GoodsService goodsService;
    private final GoodsCartService goodsCartService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GoodsCartDTO>> getAllGoodsCart(){
        return new ResponseEntity<>(goodsCartService.getAllGoodsCart(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GoodsCartDTO>> getGoodsCartByUser(@PathVariable String userId,  Authentication authentication) {
        if (!authentication.getName().equals(userId)) {
            throw new UnauthorizedUserException("자신의 장바구니만 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(goodsCartService.getGoodsCartByUser(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<GoodsCartDTO> addGoodsCart(@RequestBody GoodsCartDTO goodsCartDTO){
        return new ResponseEntity<>(goodsCartService.addGoodsCart(goodsCartDTO),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public ResponseEntity<String> deleteGoodsCart(@RequestBody List<Long> ids, Authentication authentication) {
        return new ResponseEntity<>(goodsCartService.deleteGoodsCartByIds(ids, authentication.getName()),HttpStatus.OK);
    }
}
