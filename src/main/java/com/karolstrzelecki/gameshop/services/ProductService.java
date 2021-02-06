package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.DescriptionDao;
import com.karolstrzelecki.gameshop.daos.FilterDao;
import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.dtos.TitleListingValue;
import com.karolstrzelecki.gameshop.models.Copy.Copy;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<ProductDao> getAllProducts();
    DescriptionDao getDescriptionDao(Long id);

    public Copy getCopy(Long id);
    ProductDao convert(Copy e);

    public List<ProductDao> getFilteredProducts(FilterDao dao);

}
