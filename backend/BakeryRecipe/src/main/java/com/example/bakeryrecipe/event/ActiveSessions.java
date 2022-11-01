package com.example.bakeryrecipe.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActiveSessions {

    List<String> sessions = new ArrayList<>();
    Date time;

    public ActiveSessions() {
    }

    public ActiveSessions(String sessions, Date time) {
        this.sessions.add(sessions);
        this.time = time;
    }

    public boolean isEmpty() {
        return sessions.isEmpty();
    }

    public void addSession(String session) {
        sessions.add(session);
    }

    public void removeSession(String session) {
        sessions.remove(session);
    }

    public List<String> getSessions() {
        return sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
