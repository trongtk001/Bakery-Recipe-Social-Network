package com.example.bakeryrecipe.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
    private String messageBody;

    @Column(name = "create_date", nullable = false)
    private Instant createDate;

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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }
}