package com.mz_dev.petagram.pojo;

public class ImageProfile {
    private int image;
    private int rating;

    public ImageProfile(int image, int rating) {
        this.image = image;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }
}
