package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.dtos.TitleListingValue;

import java.util.List;

public interface ProductService {

    public List<ProductDao> getAllProducts();
}
