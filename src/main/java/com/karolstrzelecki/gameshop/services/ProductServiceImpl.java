package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.ProductDao;
import com.karolstrzelecki.gameshop.models.Copy.CaseCopy;
import com.karolstrzelecki.gameshop.models.Copy.Conditions;
import com.karolstrzelecki.gameshop.models.Copy.Copy;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.models.Language;
import com.karolstrzelecki.gameshop.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CopyRepository copyRepository;

    @Override
    public List<ProductDao> getAllProducts() {

        List<Copy> copies = copyRepository.findAll();


        List<ProductDao> daos = copies.stream().map(e->{
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
        }).collect(Collectors.toList());

        List<ProductDao> nnDaos = daos.stream().filter(x -> x!=null).collect(Collectors.toList());
        return nnDaos;
    }
}
