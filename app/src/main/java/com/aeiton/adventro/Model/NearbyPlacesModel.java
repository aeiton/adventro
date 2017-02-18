package com.aeiton.adventro.Model;

/**
 * Created by User on 10-Jan-17.
 */

public class NearbyPlacesModel {

    private String caption, location;
    private int imgId;
    Double lat, lng;


    public NearbyPlacesModel(){

    }

    public NearbyPlacesModel( String location, String caption, int imgId, Double lat, Double lng){

        this.caption = caption;
        this.imgId = imgId;
        this.location = location;
        this.lat = lat;
        this.lng = lng;

    }

    public int getImgId() {
        return imgId;
    }

    public String getCaption() {
        return caption;
    }

    public String getLocation() {
        return location;
    }

    public Double getLng() {
        return lng;
    }

    public Double getLat() {
        return lat;
    }



    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}
