package com.karolstrzelecki.gameshop.dtos;

public class CopyAdderDto {
    public String game_id;
    public String platform;
    public String condition;
    public String price;
    public String copy_id;

    @Override
    public String toString() {
        return "CopyAdderDto{" +
                "game_id='" + game_id + '\'' +
                ", platform='" + platform + '\'' +
                ", condition='" + condition + '\'' +
                ", price='" + price + '\'' +
                ", copy_id='" + copy_id + '\'' +
                '}';
    }
}
