package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.GoodsDTO;
import com.dw.artgallery.repository.GoodsRepository;
import com.dw.artgallery.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/all")
    public ResponseEntity<List<GoodsDTO>> getAllGoods(){
        return new ResponseEntity<>(goodsService.getAllGoods(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GoodsDTO> getGoodsById(@PathVariable Long id){
        return new ResponseEntity<>(goodsService.getGoodsById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<GoodsDTO>> getGoodsByName(@PathVariable String name){
        return new ResponseEntity<>(goodsService.getGoodsByName(name), HttpStatus.OK);
    }

    @GetMapping ("/price/{sortOrder}")
    public ResponseEntity<List<GoodsDTO>> getGoodsSortByPrice(@PathVariable String sortOrder){
        return new ResponseEntity<>(goodsService.getGoodsSortByPrice(sortOrder),HttpStatus.OK);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<GoodsDTO> getGoodsStockById(@PathVariable Long id) {
        return new ResponseEntity<>(goodsService.getGoodsStockById(id), HttpStatus.OK);
    }
}

