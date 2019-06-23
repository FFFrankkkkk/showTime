package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
//小黑屋表
@Entity
@Table(name="blacklist")
public class BlackList extends IdEntity {
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private User    user;         //用户Id,pk
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp forbiddenTime;  //禁用开始时间
    private String reason;         //禁用理由

    public User getUser() {
        return user;
    }

    public Timestamp getForbiddenTime() {
        return forbiddenTime;
    }

    public String getReason() {
        return reason;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setForbiddenTime(Timestamp forbiddenTime) {
        this.forbiddenTime = forbiddenTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String toString(){
        return "id"+getId();
    }
}
