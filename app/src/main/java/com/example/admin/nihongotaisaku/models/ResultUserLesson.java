package com.example.admin.nihongotaisaku.models;

public class ResultUserLesson {
    int id;
    int user_id;
    int lesson_id;

    public ResultUserLesson() {
    }

    public ResultUserLesson(int id, int user_id, int lesson_id) {
        this.id = id;
        this.user_id = user_id;
        this.lesson_id = lesson_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }
}
