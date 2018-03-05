package com.example.admin.nihongotaisaku.models;

import java.io.Serializable;

public class ListLesson implements Serializable{
    String lessonTitle;
    String lessonHref;

    public ListLesson() {
    }

    public ListLesson(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public ListLesson(String lessonTitle, String lessonHref) {
        this.lessonTitle = lessonTitle;
        this.lessonHref = lessonHref;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonHref() {
        return lessonHref;
    }

    public void setLessonHref(String lessonHref) {
        this.lessonHref = lessonHref;
    }
}
