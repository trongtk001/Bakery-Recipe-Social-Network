package com.example.bakeryrecipe.api.output;

import java.util.ArrayList;
import java.util.List;

public class OnlineMember {

    private List<Long> onlineMember = new ArrayList<>();

    public List<Long> getOnlineMember() {
        return onlineMember;
    }

    public void setOnlineMember(List<Long> onlineMember) {
        this.onlineMember = onlineMember;
    }

    private Action action;

    public enum Action {
        Login,
        Logout
    }
}
