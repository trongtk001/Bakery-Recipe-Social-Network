package com.example.bakeryrecipe.api.output;

public class LoginOutput {
    private long id;

    public LoginOutput() {
    }

    public LoginOutput(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
