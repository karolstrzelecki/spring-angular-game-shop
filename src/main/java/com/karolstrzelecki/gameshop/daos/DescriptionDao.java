package com.karolstrzelecki.gameshop.daos;

import java.util.List;

public class DescriptionDao {
    public Long copy_id;
    public String game_title;
    public String alternative_title;
    public List<String> images;
    public String platform;
    public List<String> categories;
    public List<String> languages;
    public String condition;
    public String price;
    public String description;

    @Override
    public String toString() {
        return "DescriptionDao{" +
                "copy_id=" + copy_id +
                ", game_title='" + game_title + '\'' +
                ", alternative_title='" + alternative_title + '\'' +
                ", images=" + images +
                ", platform='" + platform + '\'' +
                ", categories=" + categories +
                ", languages=" + languages +
                ", condition='" + condition + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
