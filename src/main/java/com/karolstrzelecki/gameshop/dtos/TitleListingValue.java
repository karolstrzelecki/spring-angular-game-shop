package com.karolstrzelecki.gameshop.dtos;

import com.karolstrzelecki.gameshop.daos.SingleCopyDataDao;

import java.util.List;

public class TitleListingValue {

    public Long game_id;
    public String game_title;
    public int quantity;
    public List<SingleCopyDataDao> copies;

}
