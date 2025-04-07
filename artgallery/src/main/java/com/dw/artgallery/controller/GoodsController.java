//package com.dw.artgallery.controller;
//
//import com.dw.artgallery.DTO.GoodsDTO;
//import com.dw.artgallery.repository.GoodsRepository;
//import com.dw.artgallery.service.GoodsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/goods")
//public class GoodsController {
//    @Autowired
//    GoodsService goodsService;
//
//    @GetMapping("/all")
//    public ResponseEntity<List<GoodsDTO>> getAllGoods(){
//        return new ResponseEntity<>(goodsService.getAllGoods(), HttpStatus.OK);
//    }
//
//    @GetMapping("/id/{id}")
//    public ResponseEntity<GoodsDTO> getGoodsById(@PathVariable Long id){
//        return new ResponseEntity<>(goodsService.getGoodsById(id), HttpStatus.OK);
//    }
//}
