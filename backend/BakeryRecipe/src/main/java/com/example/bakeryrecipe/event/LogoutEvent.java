package com.example.bakeryrecipe.event;

/**
 * 
 * @author Sergi Almar
 */
public class LogoutEvent {
	
	private Long memberID;

	public LogoutEvent() {
	}

	public LogoutEvent(Long memberID) {
		this.memberID = memberID;
	}

	public Long getMemberID() {
		return memberID;
	}

	public void setMemberID(Long memberID) {
		this.memberID = memberID;
	}
}
