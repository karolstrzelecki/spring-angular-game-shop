package com.karolstrzelecki.gameshop.controllers;


import com.karolstrzelecki.gameshop.daos.CgFormValues;
import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.models.FileInfo;
import com.karolstrzelecki.gameshop.services.ImageService;
import com.karolstrzelecki.gameshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ImageService imageService;

    @GetMapping("all")
    public List<ProductDao> getProducts() {
        System.out.println("GET all Products");
        List<ProductDao> daos = productService.getAllProducts();

        return daos;
    }

    @GetMapping("img/{imageName}")
    ResponseEntity<?> getImage(@PathVariable("imageName") String img) {
        FileInfo fi = imageService.load(img);
        ByteArrayResource resource = new ByteArrayResource( fi.getPicbyte());

        return ResponseEntity
                .ok()
                .contentLength(fi.getPath().toFile().length())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);


    }





}
