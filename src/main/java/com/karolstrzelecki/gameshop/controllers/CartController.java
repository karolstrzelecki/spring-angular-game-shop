package com.karolstrzelecki.gameshop.controllers;

import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.dtos.CopyAdderDto;
import com.karolstrzelecki.gameshop.payload.response.MessageResponse;
import com.karolstrzelecki.gameshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("add/{id}")
    public ResponseEntity<?> addToCart(@PathVariable("id") Long id) throws AccessDeniedException {

        System.out.println(id);
      cartService.addToCart(id);
        return ResponseEntity.ok(new MessageResponse("Added to Cart successfully!"));
    }

    @GetMapping("show")
    List<ProductDao> getCartVals() throws AccessDeniedException {
        return cartService.showCart();

    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteCartItem(@PathVariable("id") Long id){


        return cartService.deleteFromCart(id);
    }

}
