package com.karolstrzelecki.gameshop.models;


import com.karolstrzelecki.gameshop.models.Copy.Copy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class ComputerGame {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long computerGameId;

    private String title;

    private String alternativeTitle;

    private String publisher;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "GameCategory", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<GameCategory> categories;

    private Integer maxNumberOfPlayers;

    private Date releaseDate;

    private Integer playersRating;

    private String ytLink;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Language", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Language> languages;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String description;//Commodity Description

    @OneToMany(mappedBy = "computerGame", cascade = CascadeType.ALL)
    private List<Copy> copies = new ArrayList<>();



    @ElementCollection
    private List<String> imageUrl = new ArrayList<String>();


    public ComputerGame() {
    }


    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getComputerGameId() {
        return computerGameId;
    }

    public void setComputerGameId(Long computerGameId) {
        this.computerGameId = computerGameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlternativeTitle() {
        return alternativeTitle;
    }

    public void setAlternativeTitle(String alternativeTitle) {
        this.alternativeTitle = alternativeTitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public Integer getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(Integer maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getPlayersRating() {
        return playersRating;
    }

    public void setPlayersRating(Integer playersRating) {
        this.playersRating = playersRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//    public void addImage(ImageModel image){
//        if(!images.contains(image)){
//            images.add(image);
//            image.setComputerGame(this);
//        }
//    }
//
//    public void removeImage(ImageModel image){
//        if(image != null){
//            if(images.contains(image)){
//                images.remove(image);
//                image.setComputerGame(null);
//            }
//        }
//    }


    public void addImageUrl(String path){
        if(imageUrl.contains(path)){
            imageUrl.add(path);
        }
    }

    public void removeImage(String path){
        if(imageUrl.contains(path) ){
            imageUrl.remove(path);
        }
    }


    public void addCopy(Copy copy) {
        if(!copies.contains(copy)){

            copies.add(copy);
            copy.setComputerGame(this);

        }
    }

    public void removeCopy(Copy copy){
        if(copy != null){
            if(copies.contains(copy)){
                copies.remove(copy);
                copy.setComputerGame(null);
            }
        }

    }

    public Set<GameCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<GameCategory> categories) {
        this.categories = categories;
    }

    public String getYtLink() {
        return ytLink;
    }

    public void setYtLink(String ytLink) {
        this.ytLink = ytLink;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }



    @Override
    public String toString() {
        return "ComputerGame{" +
                "computerGameId=" + computerGameId +
                ", title='" + title + '\'' +
                ", alternativeTitle='" + alternativeTitle + '\'' +
                ", publisher='" + publisher + '\'' +
                ", categories=" + categories +
                ", maxNumberOfPlayers=" + maxNumberOfPlayers +
                ", releaseDate=" + releaseDate +
                ", playersRating=" + playersRating +
                ", ytLink='" + ytLink + '\'' +
                ", languages=" + languages +
                ", description='" + description + '\'' +
                ", copies=" + copies +

                '}';
    }
}
