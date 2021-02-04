package com.karolstrzelecki.gameshop.dtos;


import com.karolstrzelecki.gameshop.models.GameCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ComputerGameRegisterDto {

     public String title;

    private String alternativeTitle;
//
    private String publisher;
//
//
//
    private Integer maxNumberOfPlayers;
//

    public DateHelper releaseDate;
//
    private Integer playersRating;

    private String ytLink;
//


    public String description;
    public Boolean published;
    public List <String> languages;
    public List <String> categories;



//    private MultipartFile[] files;


    @Override
    public String toString() {
        return "ComputerGameRegisterDto{" +
                "title='" + title + '\'' +
                ", alternativeTitle='" + alternativeTitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", maxNumberOfPlayers=" + maxNumberOfPlayers +
                ", releaseDate=" + releaseDate +
                ", playersRating=" + playersRating +
                ", ytLink='" + ytLink + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", languages=" + languages +
                ", categories=" + categories +
                '}';
    }
}
