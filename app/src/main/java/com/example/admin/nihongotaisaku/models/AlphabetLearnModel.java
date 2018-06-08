package com.example.admin.nihongotaisaku.models;

public class AlphabetLearnModel {
    String sound, image_association, description;
    String picture_1, picture_2, picture_3;
    int alphabet_id;

    public AlphabetLearnModel() {
    }

    public AlphabetLearnModel(int alphabet_id) {
        this.alphabet_id = alphabet_id;
    }

    public AlphabetLearnModel(String sound, String image_association, String description,
                              String picture_1, String picture_2, String picture_3) {
        this.sound = sound;
        this.image_association = image_association;
        this.description = description;
        this.picture_1 = picture_1;
        this.picture_2 = picture_2;
        this.picture_3 = picture_3;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getImage_association() {
        return image_association;
    }

    public void setImage_association(String image_association) {
        this.image_association = image_association;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_1() {
        return picture_1;
    }

    public void setPicture_1(String picture_1) {
        this.picture_1 = picture_1;
    }

    public String getPicture_2() {
        return picture_2;
    }

    public void setPicture_2(String picture_2) {
        this.picture_2 = picture_2;
    }

    public String getPicture_3() {
        return picture_3;
    }

    public void setPicture_3(String picture_3) {
        this.picture_3 = picture_3;
    }

    public int getAlphabet_id() {
        return alphabet_id;
    }

    public void setAlphabet_id(int alphabet_id) {
        this.alphabet_id = alphabet_id;
    }
}
