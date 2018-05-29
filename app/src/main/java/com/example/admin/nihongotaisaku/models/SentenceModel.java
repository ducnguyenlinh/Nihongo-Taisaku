package com.example.admin.nihongotaisaku.models;

public class SentenceModel {
    int vocabulary_id;
    String content, mean;

    public SentenceModel() {
    }

    public SentenceModel(String content, String mean) {
        this.content = content;
        this.mean = mean;
    }

    public SentenceModel(int vocabulary_id, String content, String mean) {
        this.vocabulary_id = vocabulary_id;
        this.content = content;
        this.mean = mean;
    }

    public int getVocabulary_id() {
        return vocabulary_id;
    }

    public void setVocabulary_id(int vocabulary_id) {
        this.vocabulary_id = vocabulary_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
