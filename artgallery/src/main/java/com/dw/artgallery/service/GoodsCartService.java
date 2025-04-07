package com.dw.artgallery.service;

import com.dw.artgallery.repository.GoodsCartRepository;
import com.dw.artgallery.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsCartService {
    @Autowired
    GoodsCartRepository goodsCartRepository;
}