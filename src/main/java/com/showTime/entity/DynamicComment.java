package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="dynamiccomment")
public class DynamicComment extends IdEntity {
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private User user;
    private String content;
    @JoinColumn(name="dynamicId",nullable=false)
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private  Dynamic dynamic   ;//动态表Id
    @Column(columnDefinition = "int default 0")
    private int    agree;//赞同数，默认为0
    @Column(columnDefinition = "int default 0")
    private int    disagree;//不赞同数，默认为0
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp commentTime;
    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    public int getAgree() {
        return agree;
    }

    public int getDisagree() {
        return disagree;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public void setDisagree(int disagree) {
        this.disagree = disagree;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }
}

