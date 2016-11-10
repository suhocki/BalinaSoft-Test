package com.maxim.suhockii.testapp;

/**
 * Created by hzkto on 11/7/2016.
 */

public class CategoryForAdapter {
    private int imageResource;
    private String text;
    private int id;

    public CategoryForAdapter(Integer imageResource, String text, int id) {
        this.imageResource = imageResource;
        this.text = text;
        this.id = id;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public void setImageResource(Integer imageResource) {
        this.imageResource = imageResource;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
