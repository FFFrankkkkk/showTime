package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;

@Entity
@Table(name="report")
public class Report  extends IdEntity {
    @ManyToOne(fetch= FetchType.LAZY)
    private User user;
    @ManyToOne(fetch= FetchType.LAZY)
    private Production production;
    @Column(columnDefinition = "int default 0")
    private int state;//0:审核中，-1审核失败，1审核成功
    String reportReason;//举报原因

    public int getState() {
        return state;
    }

    public User getUser() {
        return user;
    }

    public Production getProduction() {
        return production;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }
}
