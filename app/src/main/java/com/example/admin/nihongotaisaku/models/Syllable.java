package com.example.admin.nihongotaisaku.models;

import java.io.Serializable;

public class Syllable implements Serializable{
    String nihongo;
    String romaji;

    public Syllable(String nihongo, String romaji) {
        this.nihongo = nihongo;
        this.romaji = romaji;
    }

    public Syllable() {
    }

    public String getNihongo() {
        return nihongo;
    }

    public void setNihongo(String nihongo) {
        this.nihongo = nihongo;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }
}
