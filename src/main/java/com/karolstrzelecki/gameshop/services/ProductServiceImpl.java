package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.DescriptionDao;
import com.karolstrzelecki.gameshop.daos.FilterDao;
import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.models.ComputerGame;
import com.karolstrzelecki.gameshop.models.Copy.CaseCopy;
import com.karolstrzelecki.gameshop.models.Copy.Conditions;
import com.karolstrzelecki.gameshop.models.Copy.Copy;
import com.karolstrzelecki.gameshop.models.Copy.Platform;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.models.Language;
import com.karolstrzelecki.gameshop.repository.ComputerGameRepository;
import com.karolstrzelecki.gameshop.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CopyRepository copyRepository;


    @Autowired
    ComputerGameRepository computerGameRepository;

    @Override
    public List<ProductDao> getAllProducts() {

        Optional<List<Copy>> optCopies = copyRepository.findAllByShoppingCartIsNull();
        List<Copy> copies= optCopies.get();


        List<ProductDao> daos = copies.stream().map(e->{
           ProductDao dao = convert(e);
           return dao;
        }).filter(x -> x!=null).collect(Collectors.toList());

//        List<ProductDao> nnDaos = daos.stream().filter(x -> x!=null).collect(Collectors.toList());
        return daos;
    }


    @Override
    public Copy getCopy(Long id) {
        Optional<Copy> cp = copyRepository.findById(id);
        if(cp.isPresent()){
            Copy cp1 = cp.get();
            return cp1;
        }
        return null;
    }

    @Override
    public ProductDao convert(Copy e){
        if(e.getComputerGame() != null){
            ProductDao dao = new ProductDao();
            dao.copy_id= e.getCopyId();

            dao.game_title=e.getComputerGame().getTitle();
            Set<GameCategory> cats= e.getComputerGame().getCategories();
            List<GameCategory> catList = new ArrayList<>();
            catList.addAll(cats);
            List<String>catVals= catList.stream().map(c->{
                String str = "";
                str = c.getName();
                return str;
            }).collect(Collectors.toList());
            dao.categories= catVals;
            List<Language> langs = new ArrayList<>();
            langs.addAll(e.getComputerGame().getLanguages());
            List<String>langVals = langs.stream().map(m->{
                String s = m.description;
                return s;
            }).collect(Collectors.toList());
            dao.languages= langVals;
            dao.price = String.valueOf(e.getPrice().doubleValue());
            dao.condition = Conditions.brandnew.name();
            dao.image = e.getComputerGame().getImageUrl().get(0);
            dao.platform = e.getPlatform().getName();

            return dao;}
        return null;
    }

    @Override
    public DescriptionDao getDescriptionDao(Long id) {
        Optional<Copy> cp = copyRepository.findById(id);
        if(!cp.isPresent()){

            return null;
        }
        Copy e = cp.get();

        if(e.getComputerGame() != null){

            DescriptionDao dao = new DescriptionDao();
            dao.copy_id= e.getCopyId();

            dao.game_title=e.getComputerGame().getTitle();
            dao.alternative_title=e.getComputerGame().getAlternativeTitle();
            Set<GameCategory> cats= e.getComputerGame().getCategories();
            List<GameCategory> catList = new ArrayList<>();
            catList.addAll(cats);
            List<String>catVals= catList.stream().map(c->{
                String str = "";
                str = c.getName();
                return str;
            }).collect(Collectors.toList());
            dao.categories= catVals;
            List<Language> langs = new ArrayList<>();
            langs.addAll(e.getComputerGame().getLanguages());
            List<String>langVals = langs.stream().map(m->{
                String s = m.description;
                return s;
            }).collect(Collectors.toList());
            dao.languages= langVals;
            dao.price = String.valueOf(e.getPrice().doubleValue());
            dao.condition = Conditions.brandnew.name();
            dao.images = e.getComputerGame().getImageUrl();
            dao.platform = e.getPlatform().getName();
            dao.description = e.getComputerGame().getDescription();

            return dao;}

        return null;
    }

    @Override
    public List<ProductDao> getFilteredProducts(FilterDao dao) {
//        List<String> catNames = dao.categories;
        System.out.println(dao.categories);
        System.out.println(dao.platform);
        Optional<List<Copy>>copies;

        if(dao.categories== null){
            copies = copyRepository.findAllByShoppingCartIsNullAndPlatform(Platform.get(dao.platform));
            System.out.println(copies);
        }else{
            Set<GameCategory> cats = dao.categories.stream().map(e-> GameCategory.get(e)).collect(Collectors.toSet());
            copies = copyRepository.findAllByShoppingCartIsNullAndComputerGame_CategoriesInAndPlatformIs(cats, Platform.get(dao.platform));
            System.out.println(copies);
        }



        List<Copy> copies1= copies.get();


        List<ProductDao> daos = copies1.stream().map(e->{
            ProductDao dao1 = convert(e);
            return dao1;
        }).filter(x -> x!=null).collect(Collectors.toList());

        return daos;
    }
}
