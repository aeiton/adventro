package com.aeiton.adventro.Model;

/**
 * Created by User on 10-Jan-17.
 */

public class TimeLineModel {

    private String caption, location, time;
    private byte[] image, proPic;

    public TimeLineModel() {

    }

    public TimeLineModel(String caption, String location, String time, byte[] proPic, byte[] image) {

        this.caption = caption;
        this.proPic = proPic;
        this.location = location;
        this.time = time;
        this.image = image;

    }

    public byte[] getProPic() {
        return proPic;
    }

    public void setProPic(byte[] proPic) {
        this.proPic = proPic;
    }

    public String getCaption() {
        return caption;
    }

    public String getLocation() {
        return location;
    }

    public byte[] getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }
}
