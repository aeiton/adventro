package com.aeiton.adventro.Adapters;

/**
 * Created by User on 10-Jan-17.
 */

public class NewsFeedModel {

    private String name, caption, location, title;
    int propic, img, like, comment;
    Boolean likeStatus = false;
    Double lat, lng;

    int type;

    public NewsFeedModel(){

    }

    public NewsFeedModel(int type, String name,   String location ,String caption, int propic, int img, int like, int comment, Double lat, Double lng){

        this.type = type;
        this.name = name;
        this.caption = caption;
        this.propic = propic;
        this.img = img;
        this.like = like;
        this.comment = comment;
        this.lat = lat;
        this.lng = lng;
        this.location = location;
    }

    public NewsFeedModel(int type, String name, String title, int propic, int image, int like, int comment ){

        this.type = type;

        this.name = name;
        this.title = title;
        this.propic = propic;
        this.like = like;
        this.comment = comment;
        this.img = image;

    }

    public NewsFeedModel(int type, String name, String location, int propic, int like, int comment, Double lat, Double lng ){

        this.type = type;

        this.name = name;
        this.location = location;
        this.propic = propic;
        this.like = like;
        this.comment = comment;
        this.lat = lat;
        this.lng = lng;

    }

    public int getComment() {
        return comment;
    }

    public int getImg() {
        return img;
    }

    public int getLike() {
        return like;
    }

    public int getPropic() {
        return propic;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public int getType() {
        return type;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void likePost(){
        likeStatus = true;
    }
    public void unlikePost(){
        likeStatus = false;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }
}
