package com.showTime.entity;

import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.IsShow;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name="history")
public class History extends IdEntity {
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private User   user;        //用户Id,pk
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp historyTime; //观看历史时间
    private IsShow isShow;      //是否公开，公开：1，不公开：0;
    private String passTime;    //观看时长
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private Production  production;

    public User getUser() {
        return user;
    }

    public Timestamp getHistoryTime() {
        return historyTime;
    }
    public IsShow getIsShow() {
        return isShow;
    }

    public String getPassTime() {
        return passTime;
    }

    public Production getProduction() {
        return production;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHistoryTime(Timestamp historyTime) {
        this.historyTime = historyTime;
    }

    public void setIsShow(IsShow isShow) {
        this.isShow = isShow;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
}
