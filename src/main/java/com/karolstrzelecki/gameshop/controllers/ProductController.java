package com.karolstrzelecki.gameshop.controllers;


import com.karolstrzelecki.gameshop.daos.*;
import com.karolstrzelecki.gameshop.dtos.CopyAdderDto;
import com.karolstrzelecki.gameshop.models.Copy.Conditions;
import com.karolstrzelecki.gameshop.models.Copy.Platform;
import com.karolstrzelecki.gameshop.models.FileInfo;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.models.Language;
import com.karolstrzelecki.gameshop.models.User;
import com.karolstrzelecki.gameshop.services.ImageService;
import com.karolstrzelecki.gameshop.services.ProductService;
import com.karolstrzelecki.gameshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @GetMapping("all")
    public List<ProductDao> getProducts() throws AccessDeniedException {
        System.out.println("GET all Products");
        List<ProductDao> daos = productService.getAllProducts();
//        User user = userService.getUser();

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

    @GetMapping("description/{id}")
    DescriptionDao getDescription(@PathVariable("id") String id){

        Long val = Long.valueOf(id);
        DescriptionDao desc = productService.getDescriptionDao(val);

        System.out.println(desc);

        return desc;
    }




    @GetMapping("/vals")
    public ProductFormValues getUsers() {

        List<GameCategory> categories = Arrays.asList(GameCategory.values());
        List<String> stringCategories = categories.stream().map(e -> e.name).collect(Collectors.toList());
        List<Language> languages = Arrays.asList(Language.values());
        List<String> stringLanguages = languages.stream().map(e -> e.description).collect(Collectors.toList());
        List<Conditions> conditions = Arrays.asList(Conditions.values());
        List<String> stringConditions = conditions.stream().map(e -> e.description).collect(Collectors.toList());
        List<Platform> platforms = Arrays.asList(Platform.values());
        List<String> stringPlatforms = platforms.stream().map(e -> e.name).collect(Collectors.toList());

        ProductFormValues pfv = new ProductFormValues();
        pfv.categories= stringCategories;
        pfv.languages=stringLanguages;
        pfv.conditions = stringConditions;
        pfv.platforms = stringPlatforms;
        return pfv;
    }


    @GetMapping("filter")
    public ResponseEntity<?> filterProducts(@RequestParam(name="categories", required=false) List<String> categories, @RequestParam("platform") String platform){
        System.out.println(categories);
        System.out.println(platform);
        FilterDao filterDao = new FilterDao();
        filterDao.categories = categories;
        filterDao.platform = platform;

        List<ProductDao> daos = productService.getFilteredProducts(filterDao);

         return ResponseEntity
                .ok()
                .body(daos);


    }









}
