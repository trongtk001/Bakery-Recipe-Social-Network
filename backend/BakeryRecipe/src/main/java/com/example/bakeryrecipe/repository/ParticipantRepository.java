package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.event.ActiveSessions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Repository
public class ParticipantRepository {

	private Map<String, ActiveSessions> onlineUsers = new HashMap<>();

	public void add(String memberName, String sessionId) {
		ActiveSessions sessions = onlineUsers.get(memberName);
		if (isNull(sessions)) {
			sessions = new ActiveSessions(sessionId, new Date());
			onlineUsers.put(memberName, sessions);
		} else {
			sessions.addSession(sessionId);
		}
	}

	public void removeParticipant(String sessionId) {
		try {
			onlineUsers.forEach((s, activeSessions) -> {
				activeSessions.removeSession(sessionId);
				if (activeSessions.isEmpty()) {
					onlineUsers.remove(s);
					throw new RuntimeException();
				}
			});
		} catch (Exception e) {}
	}

	public List<String> getOnlineUser() {
		return new ArrayList<>(onlineUsers.keySet());
	}
}
