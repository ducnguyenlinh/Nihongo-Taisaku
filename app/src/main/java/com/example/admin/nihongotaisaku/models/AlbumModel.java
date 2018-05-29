package com.example.admin.nihongotaisaku.models;

public class AlbumModel {
    private String nameAlbum;
    private int thumbnail;
    private int numOfImages;

    public AlbumModel() {
    }

    public AlbumModel(String nameAlbum, int thumbnail, int numOfImages) {
        this.nameAlbum = nameAlbum;
        this.thumbnail = thumbnail;
        this.numOfImages = numOfImages;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getNumOfImages() {
        return numOfImages;
    }

    public void setNumOfImages(int numOfImages) {
        this.numOfImages = numOfImages;
    }
}
