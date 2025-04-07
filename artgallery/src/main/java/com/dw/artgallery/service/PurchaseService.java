package com.dw.artgallery.service;

import com.dw.artgallery.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
}
