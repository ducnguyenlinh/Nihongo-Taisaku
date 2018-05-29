package com.example.admin.nihongotaisaku.models;


public class FeedbackModel {
    int user_id;
    String object_class;
    int object_id;
    String content;

    public FeedbackModel(int user_id, String object_class, int object_id, String content) {
        this.user_id = user_id;
        this.object_class = object_class;
        this.object_id = object_id;
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getObject_class() {
        return object_class;
    }

    public void setObject_class(String object_class) {
        this.object_class = object_class;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
