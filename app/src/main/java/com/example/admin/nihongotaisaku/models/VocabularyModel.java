package com.example.admin.nihongotaisaku.models;

public class VocabularyModel {
    int id, lesson_id, is_learn;
    String japanese, spell, mean, picture;

    public VocabularyModel() {
    }

    public VocabularyModel(int lesson_id, String japanese, String spell, String mean,
                           String picture) {
        this.lesson_id = lesson_id;
        this.japanese = japanese;
        this.spell = spell;
        this.mean = mean;
        this.picture = picture;
    }

    public VocabularyModel(int id, int lesson_id, int is_learn, String japanese, String spell,
                           String mean, String picture) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.is_learn = is_learn;
        this.japanese = japanese;
        this.spell = spell;
        this.mean = mean;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
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

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getIs_learn() {
        return is_learn;
    }

    public void setIs_learn(int is_learn) {
        this.is_learn = is_learn;
    }
}
