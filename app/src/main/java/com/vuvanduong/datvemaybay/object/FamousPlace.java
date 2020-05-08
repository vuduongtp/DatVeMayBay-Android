package com.vuvanduong.datvemaybay.object;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class FamousPlace implements Serializable {
    private String Name;
    private int Image;

    public FamousPlace(String name, int image) {
        Name = name;
        Image = image;
    }

    public FamousPlace() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName().toString();
    }
}
