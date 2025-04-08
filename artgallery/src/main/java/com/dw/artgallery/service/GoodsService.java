package com.dw.artgallery.service;

import com.dw.artgallery.DTO.GoodsDTO;
import com.dw.artgallery.enums.SortOrder;
import com.dw.artgallery.model.Goods;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.GoodsRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.exception.PermissionDeniedException;
import com.dw.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    UserService userService;

    public List<GoodsDTO> getAllGoods() {
        List<Goods> goodsList = goodsRepository.findAll();

        return goodsList.stream()
                .map(GoodsDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public GoodsDTO getGoodsById(Long id){
        return goodsRepository.findById(id)
                .map(GoodsDTO::fromEntity)
                .orElseThrow(()-> new ResourceNotFoundException("해당 굿즈가 존재하지 않습니다"));
    }

    public List<GoodsDTO> getGoodsByName(String name){
        return goodsRepository.findByNameLike(name).stream().map(GoodsDTO::fromEntity).toList();
    }

    public List<GoodsDTO>getGoodsSortByPrice(SortOrder sortOrder) {
        List<Goods> result;

        if (sortOrder == SortOrder.ASC) {
            result = goodsRepository.findAllByOrderByPriceAsc();
        } else {
            result = goodsRepository.findAllByOrderByPriceDesc();
        }
        return result.stream().map(GoodsDTO::fromEntity).toList();
    }

    public int getGoodsStockById(Long id){
        GoodsDTO goods = goodsRepository.findById(id)
                .map(GoodsDTO::fromEntity)
                .orElseThrow(()-> new ResourceNotFoundException("해당 굿즈가 존재하지 않습니다"));

        return goods.getStock();
    }

    public GoodsDTO addGoods(GoodsDTO goodsDTO, HttpServletRequest request){

        Goods goods = new Goods();
        goods.setName(goodsDTO.getName());
        goods.setImgUrlList(goodsDTO.getImgUrlList());
        goods.setDescription(goodsDTO.getDescription());
        goods.setPrice(goodsDTO.getPrice());
        goods.setStock(goodsDTO.getStock());

        Goods saveGoods = goodsRepository.save(goods);

        return GoodsDTO.fromEntity(saveGoods);
    }
}
