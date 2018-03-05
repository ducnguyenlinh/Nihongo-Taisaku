package com.example.admin.nihongotaisaku.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String created_at;
    private String updated_at;
    private String authentication_token;

    public User(String name, String email, String password, String password_confirmation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public User(int id, String name, String email, String authentication_token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.authentication_token = authentication_token;
    }

    public User(int id, String name, String email, String password, String password_confirmation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public User(int id, String name, String email, String password, String password_confirmation,
                String created_at, String updated_at, String authentication_token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.authentication_token = authentication_token;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getAuthentication_token() {
        return authentication_token;
    }
}
