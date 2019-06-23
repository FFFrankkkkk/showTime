package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="productionComment")
public class productionComment extends IdEntity {
    private String content;
    @JoinColumn(name="productionId",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private Production  production;//作品表Id,fk
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private User   user;//赞同数，默认为0
    @Column(columnDefinition = "int default 0")
    private int    agree;//不]赞同数，默认为0
    @Column(columnDefinition = "int default 0")
    private int    disagree;//不赞同数，默认为0
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp CommentTime;
    public String getContent() {
        return content;
    }

    public Production getProduction() {
        return production;
    }

    public User getUser() {
        return user;
    }

    public int getDisagree() {
        return disagree;
    }

    public Timestamp getCommentTime() {
        return CommentTime;
    }

    public int getAgree() {
        return agree;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public void setDisagree(int disagree) {
        this.disagree = disagree;
    }

    public void setCommentTime(Timestamp commentTime) {
        CommentTime = commentTime;
    }
}
