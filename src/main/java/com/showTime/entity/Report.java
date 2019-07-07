package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class Report  extends IdEntity {
    @ManyToOne(fetch= FetchType.LAZY)
    private User user;
    @ManyToOne(fetch= FetchType.LAZY)
    private Production production;
    String reportReason;//举报原因
}
