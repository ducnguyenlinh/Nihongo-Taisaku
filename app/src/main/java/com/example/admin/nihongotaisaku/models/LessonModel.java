package com.example.admin.nihongotaisaku.models;

public class LessonModel {
    Integer id;
    String content;

    public LessonModel() {
    }

    public LessonModel(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public LessonModel(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
