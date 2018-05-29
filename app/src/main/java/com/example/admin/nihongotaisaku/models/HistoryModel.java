package com.example.admin.nihongotaisaku.models;

public class HistoryModel {
    int user_id;
    String object_class;
    int object_id;
    String object_content;
    String created_at;

    public HistoryModel() {
    }

    public HistoryModel(int user_id, String object_class, int object_id) {
        this.user_id = user_id;
        this.object_class = object_class;
        this.object_id = object_id;
    }

    public HistoryModel(int user_id, String object_class, int object_id, String object_content, String created_at) {
        this.user_id = user_id;
        this.object_class = object_class;
        this.object_id = object_id;
        this.object_content = object_content;
        this.created_at = created_at;
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

    public String getObject_content() {
        return object_content;
    }

    public void setObject_content(String object_content) {
        this.object_content = object_content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
