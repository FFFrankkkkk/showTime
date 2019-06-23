package com.showTime.entity;

import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.IsShow;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name="dynamic")
public class Dynamic extends IdEntity {
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private User    user;
    private String content;
    private String contentLink;//转发内容链接
    private String imgUrl;//转发内容链接
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp   publishTime;//发布时间
    @Column(columnDefinition = "int default 0")
    private int    agree;//赞同数，默认为0
    @Column(columnDefinition = "int default 0")
    private int    disagree;//不赞同数，默认为0
    @Column(columnDefinition = "int default 0")
    private int    account;//评论数，默认为0
    private IsShow isShow;//是否公开，公开：1，不公开：0

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getContentLink() {
        return contentLink;
    }

    public String getImgUrl() {
        return imgUrl;
    }


    public Timestamp getPublishTime() {
        return publishTime;
    }

    public int getAgree() {
        return agree;
    }

    public int getDisagree() {
        return disagree;
    }

    public int getAccount() {
        return account;
    }

    public IsShow getIsShow() {
        return isShow;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public void setDisagree(int disagree) {
        this.disagree = disagree;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setIsShow(IsShow isShow) {
        this.isShow = isShow;
    }
}
