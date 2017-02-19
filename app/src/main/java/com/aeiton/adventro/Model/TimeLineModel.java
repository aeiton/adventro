package com.aeiton.adventro.Model;

/**
 * Created by User on 10-Jan-17.
 */

public class TimeLineModel {

    private String caption, location, time;
    private int proPic, image;

    public TimeLineModel(){

    }

    public TimeLineModel(String caption, String location, String time, int proPic, int image){

        this.caption = caption;
        this.proPic = proPic;
        this.location = location;
        this.time = time;
        this.image = image;

    }

    public int getProPic() {
        return proPic;
    }

    public String getCaption() {
        return caption;
    }

    public void setProPic(int proPic) {
        this.proPic = proPic;
    }

    public String getLocation() {
        return location;
    }

    public int getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }
}
