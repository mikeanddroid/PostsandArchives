package com.mike.givemewingzz.postsandarchives.appmodel.media.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 1/12/2016.
 */
public class MediaImagesThumb extends RealmObject {

    @SerializedName("url")
    private String imageURL;

    private int width;

    private int height;

    public MediaImagesThumb() {
    }

    public MediaImagesThumb(MediaImagesThumb mediaImagesStandard) {
        this.imageURL = mediaImagesStandard.getImageURL();
        this.width = mediaImagesStandard.getWidth();
        this.height = mediaImagesStandard.getHeight();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
