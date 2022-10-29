package com.example.bakeryrecipe.event;

import java.util.Date;

/**
 * 
 * @author Sergi Almar
 */
public class LoginEvent {

	private Long memberID;
	private Date time;

	public LoginEvent() {
	}

	public LoginEvent(Long memberID, Date time) {
		this.memberID = memberID;
		this.time = time;
	}

	public Long getMemberID() {
		return memberID;
	}

	public void setMemberID(Long memberID) {
		this.memberID = memberID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
