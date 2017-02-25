package com.aeiton.adventro.Model;

/**
 * Created by User on 10-Jan-17.
 */

public class FeedTimeLineModel {

    private String title;
    private int proPic;

    public FeedTimeLineModel() {

    }

    public FeedTimeLineModel(String title, int proPic) {

        this.title = title;
        this.proPic = proPic;

    }

    public int getProPic() {
        return proPic;
    }

    public void setProPic(int proPic) {
        this.proPic = proPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
