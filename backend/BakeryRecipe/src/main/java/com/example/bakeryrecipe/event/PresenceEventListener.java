package com.example.bakeryrecipe.event;

import com.example.bakeryrecipe.repository.ParticipantRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Listener to track user presence.
 * Sends notifications to the login destination when a connected event is received
 * and notifications to the logout destination when a disconnect event is received
 *
 * @author Sergi Almar
 */
public class PresenceEventListener {

	private ParticipantRepository participantRepository;

	private SimpMessagingTemplate messagingTemplate;

	private String loginDestination;

	private String logoutDestination;

	public PresenceEventListener(SimpMessagingTemplate messagingTemplate, ParticipantRepository participantRepository) {
		this.messagingTemplate = messagingTemplate;
		this.participantRepository = participantRepository;
	}

	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String token = getUserId(accessor);


//		LoginEvent loginEvent = new LoginEvent();
//		messagingTemplate.convertAndSend(loginDestination, loginEvent);
//
//		// We store the session as we need to be idempotent in the disconnect event processing
//		participantRepository.add(accessor.getSessionId(), loginEvent);
	}

	private String getUserId(StompHeaderAccessor accessor) {
		GenericMessage<?> generic = (GenericMessage<?>) accessor.getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
		if (nonNull(generic)) {
			SimpMessageHeaderAccessor nativeAccessor = SimpMessageHeaderAccessor.wrap(generic);
			List<String> userIdValue = nativeAccessor.getNativeHeader("Authorization");

			return isNull(userIdValue) ? null : userIdValue.stream().findFirst().orElse(null);
		}

		return null;
	}

	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {

		Optional.ofNullable(participantRepository.getParticipant(event.getSessionId()))
				.ifPresent(login -> {
					messagingTemplate.convertAndSend(logoutDestination, new LogoutEvent());
					participantRepository.removeParticipant(event.getSessionId());
				});
	}

	public void setLoginDestination(String loginDestination) {
		this.loginDestination = loginDestination;
	}

	public void setLogoutDestination(String logoutDestination) {
		this.logoutDestination = logoutDestination;
	}
}
