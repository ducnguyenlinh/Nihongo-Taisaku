package com.example.admin.nihongotaisaku.models;

public class AlphabetModel {
    int id;
    String japanese;
    String spell;
    int classify;

    public AlphabetModel() {

    }

    public AlphabetModel(String japanese, String spell) {
        this.japanese = japanese;
        this.spell = spell;
    }

    public AlphabetModel(int id, String japanese, String spell, int classify) {
        this.id = id;
        this.japanese = japanese;
        this.spell = spell;
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

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }
}
