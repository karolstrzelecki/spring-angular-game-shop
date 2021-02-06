package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.ProductDao;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CartService {

     void addToCart(Long productVariantId) throws AccessDeniedException;

     List<ProductDao> showCart () throws AccessDeniedException;

     boolean deleteFromCart(Long id);

     String getCartSum();
}
