package com.example.bakeryrecipe.event;

import com.example.bakeryrecipe.authentication.JwtUtils;
import com.example.bakeryrecipe.repository.ParticipantRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static java.util.Objects.nonNull;

@Component
public class PresenceEventListener {

	private ParticipantRepository participantRepository;

	private SimpMessagingTemplate messagingTemplate;

	private JwtUtils jwtUtils;

	private UserDetailsService userDetailsService;


	public PresenceEventListener(ParticipantRepository participantRepository, SimpMessagingTemplate messagingTemplate, JwtUtils jwtUtils, UserDetailsService userDetailsService) {
		this.participantRepository = participantRepository;
		this.messagingTemplate = messagingTemplate;
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;
	}

	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String token = accessor.getNativeHeader("Authorization").get(0);
		if (nonNull(token)) {
			if (jwtUtils.validateJwtToken(token.substring(7))) {
				String username = jwtUtils.getUserNameFromJwtToken(token.substring(7));
				if (nonNull(username)) {
					UserDetails user = userDetailsService.loadUserByUsername(username);
					if (nonNull(user)) {
						participantRepository.add(user.getUsername(), accessor.getSessionId());
					}
				}
			} 
		}
	}


	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		participantRepository.removeParticipant(event.getSessionId());
		messagingTemplate.convertAndSend("/app/online", participantRepository.getOnlineUser());
	}
}
