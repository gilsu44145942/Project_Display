package com.dw.artgallery.service;

import com.dw.artgallery.DTO.GoodsCartDTO;
import com.dw.artgallery.repository.GoodsCartRepository;
import com.dw.artgallery.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsCartService {
    private final GoodsCartRepository goodsCartRepository;

    public List<GoodsCartDTO>getAllGoodsCart(){
        return goodsCartRepository.findAll().stream().map(GoodsCartDTO::fromEntity).toList();
    }


}