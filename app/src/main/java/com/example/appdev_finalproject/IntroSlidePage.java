package com.example.appdev_finalproject;

public class IntroSlidePage {

    private int imageResource;
    private String title;

    public IntroSlidePage(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
}
