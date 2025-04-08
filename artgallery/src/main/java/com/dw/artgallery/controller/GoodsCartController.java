//package com.dw.artgallery.controller;
//
//import com.dw.artgallery.DTO.GoodsCartDTO;
//import com.dw.artgallery.DTO.GoodsDTO;
//import com.dw.artgallery.model.Goods;
//import com.dw.artgallery.model.GoodsCart;
//import com.dw.artgallery.service.GoodsCartService;
//import com.dw.artgallery.service.GoodsService;
//import org.apache.coyote.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/cart")
//public class GoodsCartController {
//    @Autowired
//    GoodsService goodsService;
//
//    @Autowired
//    GoodsCartService goodsCartService;
//
//    @GetMapping
//    public ResponseEntity<List<GoodsCartDTO>> getAllGoodsCart(){
//        return new ResponseEntity<>(goodsCartService.getAllGoodsCart(), HttpStatus.OK);
//    }
//
//    @GetMapping("/id/{id}")
//    public ResponseEntity<GoodsCartDTO> getGoodsCartById(@PathVariable Long id){
//        return new ResponseEntity<>(goodsCartService.getGoodsCartById(id), HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<GoodsCartDTO> addGoodsCart(@RequestBody GoodsCartDTO goodsCartDTO){
//        return new ResponseEntity<>(goodsCartService.addGoodsCart(goodsCartDTO),HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteGoodsCart(@PathVariable Long id){
//        return new ResponseEntity<>(goodsCartService.deleteGoodsCart(id), HttpStatus.OK);
//    }
//}
