package com.example.admin.nihongotaisaku.models;

public class AlphabetModel {
    int id;
    String japanese, spell, image_writing, image_compare, sound;
    int classify;

    public AlphabetModel() {

    }

    public AlphabetModel(String japanese, String spell) {
        this.japanese = japanese;
        this.spell = spell;
    }

    public AlphabetModel(int id, String japanese, String spell, String image_writing,
                         String image_compare, String sound, int classify) {
        this.id = id;
        this.japanese = japanese;
        this.spell = spell;
        this.image_writing = image_writing;
        this.image_compare = image_compare;
        this.sound = sound;
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getImage_writing() {
        return image_writing;
    }

    public void setImage_writing(String image_writing) {
        this.image_writing = image_writing;
    }

    public String getImage_compare() {
        return image_compare;
    }

    public void setImage_compare(String image_compare) {
        this.image_compare = image_compare;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
