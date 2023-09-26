package com.mz_dev.petagram.pojo;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private int image;
    private int rating;

    public Pet(String name, int image, int rating) {
        this.name = name;
        this.image = image;
        this.rating = rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }
}
