package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name="massage")
public class Massage extends IdEntity {
    private String content;
    private int    messageState; //接收转态
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp messageTime;  //发送时间
    private int    messageType;  //信息类型
    @JoinColumn(name="fromUserId",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private User    fromUser;  //发送者id，外键
    @JoinColumn(name="toUserId",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private User    toUser;  //接收者id，外键

    public String getContent() {
        return content;
    }

    public int getMessageState() {
        return messageState;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public int getMessageType() {
        return messageType;
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessageState(int messageState) {
        this.messageState = messageState;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
