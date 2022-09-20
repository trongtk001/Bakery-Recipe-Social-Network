package com.example.bakeryrecipe.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Message {
    @Id
    @Column(name = "massage_id", nullable = false)
    private Long id;

    @Column(name = "massage_body", nullable = false, length = 500)
    private String massageBody;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_sender_id", nullable = false)
    private Member memberSender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_receiver_id", nullable = false)
    private Member memberReceiver;

    @Column(name = "mss_create_date", nullable = false)
    private Instant mssCreateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMassageBody() {
        return massageBody;
    }

    public void setMassageBody(String massageBody) {
        this.massageBody = massageBody;
    }

    public Member getMemberSender() {
        return memberSender;
    }

    public void setMemberSender(Member memberSender) {
        this.memberSender = memberSender;
    }

    public Member getMemberReceiver() {
        return memberReceiver;
    }

    public void setMemberReceiver(Member memberReceiver) {
        this.memberReceiver = memberReceiver;
    }

    public Instant getMssCreateDate() {
        return mssCreateDate;
    }

    public void setMssCreateDate(Instant mssCreateDate) {
        this.mssCreateDate = mssCreateDate;
    }

}