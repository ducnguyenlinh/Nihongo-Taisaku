package com.example.admin.nihongotaisaku.models;

public class AlphabetWritingModel {
    int alphabet_id;
    String image_writing;
    String image_compare;

    public AlphabetWritingModel() {
    }

    public AlphabetWritingModel(int alphabet_id, String image_writing) {
        this.alphabet_id = alphabet_id;
        this.image_writing = image_writing;
    }

    public AlphabetWritingModel(int alphabet_id, String image_writing, String image_compare) {
        this.alphabet_id = alphabet_id;
        this.image_writing = image_writing;
        this.image_compare = image_compare;
    }

    public int getAlphabet_id() {
        return alphabet_id;
    }

    public void setAlphabet_id(int alphabet_id) {
        this.alphabet_id = alphabet_id;
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
}
