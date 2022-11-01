package com.example.bakeryrecipe.api.input;

public class GetMessageInput {
    private long memberID;
    private long friendID;

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    public long getFriendID() {
        return friendID;
    }

    public void setFriendID(long friendID) {
        this.friendID = friendID;
    }
}
