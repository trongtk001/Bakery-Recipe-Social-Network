package com.example.bakeryrecipe.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class MessageDTO implements Serializable {

    private Long id;
    private Long senderID;
    private Long receiverID;
    private String massageBody;
    private Instant mssCreateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
    }

    public String getMassageBody() {
        return massageBody;
    }

    public void setMassageBody(String massageBody) {
        this.massageBody = massageBody;
    }

    public Instant getMssCreateDate() {
        return mssCreateDate;
    }

    public void setMssCreateDate(Instant mssCreateDate) {
        this.mssCreateDate = mssCreateDate;
    }
}
