package com.doubleclick.ecommerce.model;

public class Allprodusts {

    private String name;
    private String image;

    public int getDrwableimage() {
        return drwableimage;
    }

    public void setDrwableimage(int drwableimage) {
        this.drwableimage = drwableimage;
    }

    private int drwableimage;

    public Allprodusts(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Allprodusts() {
    }

    public Allprodusts(String name, int drwableimage) {
        this.name = name;
        this.drwableimage = drwableimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Allprodusts{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
