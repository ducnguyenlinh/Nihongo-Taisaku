package com.example.admin.nihongotaisaku.models;

public class AlbumModel {
    private String image, japanese, mean, sound;

    public AlbumModel() {
    }

    public AlbumModel(String image, String japanese, String mean, String sound) {
        this.image = image;
        this.japanese = japanese;
        this.mean = mean;
        this.sound = sound;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
