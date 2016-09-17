package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects;

public class Modules {
    private String username;
    private String module;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(int endorsements) {
        this.endorsements = endorsements;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int endorsements;
    private int rating;

}
