package com.example.bakeryrecipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "massage_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_sender_id", nullable = false)
    private Member memberSender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_receiver_id", nullable = false)
    private Member memberReceiver;

    @Column(name = "massage_body", columnDefinition = "nvarchar(500)", nullable = false, length = 500)
    private String massageBody;

    @Column(name = "mss_create_date", nullable = false)
    private Instant mssCreateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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