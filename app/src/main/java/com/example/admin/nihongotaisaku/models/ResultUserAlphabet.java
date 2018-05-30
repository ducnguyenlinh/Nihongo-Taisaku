package com.example.admin.nihongotaisaku.models;

public class ResultUserAlphabet {
    int id;
    int user_id;
    int alphabet_id;

    public ResultUserAlphabet() {
    }

    public ResultUserAlphabet(int id, int user_id, int alphabet_id) {
        this.id = id;
        this.user_id = user_id;
        this.alphabet_id = alphabet_id;
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

    public int getAlphabet_id() {
        return alphabet_id;
    }

    public void setAlphabet_id(int alphabet_id) {
        this.alphabet_id = alphabet_id;
    }
}
