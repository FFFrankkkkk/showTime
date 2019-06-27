package com.showTime.entity;


import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.Model;
import com.showTime.common.tools.Recommend;
import com.showTime.entity.Subclass;
import com.showTime.entity.Category;
import com.showTime.common.tools.IsShow;
import com.showTime.common.tools.State;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="production")
public class Production extends IdEntity {
    private String address;//作品的链接
    private String title;
    private Model model;        //0或1，0为儿童级别，1为成人级别
    private String img;         //作品封面链接
    private String context;     //介绍作品内容
    private Date   checkTime;   //审核时间
    @Column(columnDefinition = "int default 0")
    private State  state;       //审核状态,未审核为0，不通过:0；通过:1
    @ManyToOne(fetch= FetchType.LAZY)
    private Category category;
    @ManyToOne(fetch= FetchType.LAZY)
    private Subclass subclass;//分类名称
    @Column(columnDefinition = "int default 0")
    private int    account;     //浏览量，默认为0
    @Column(columnDefinition = "int default 0")
    private int    agree;       //赞同数，默认为0
    @Column(columnDefinition = "int default 0")
    private int    disagree;       //不赞同数，默认为0
    private IsShow isShow;       //是否公开，公开：1，不公开：0
    @Column(columnDefinition = "int default 0")
    private Recommend recommend;       //是否推荐，推荐：1，不公开：0
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY)
    private User    user;       //用户id，外键，关联user表
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinTable(name="production_favorite",
            joinColumns = {@JoinColumn(name = "production_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "favorite_id",referencedColumnName="id")})
    private Favorite  favorite;
    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public Model getModel() {
        return model;
    }

    public IsShow getIsShow() {
        return isShow;
    }

    public Recommend getRecommend() {
        return recommend;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public String getImg() {
        return img;
    }

    public String getContext() {
        return context;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public State getState() {
        return state;
    }

    public Category getCategory() {
        return category;
    }

    public Subclass getSubclass() {
        return subclass;
    }

    public User getUser() {
        return user;
    }

    public int getAccount() {
        return account;
    }

    public int getAgree() {
        return agree;
    }

    public int getDisagree() {
        return disagree;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setRecommend(Recommend recommend) {
        this.recommend = recommend;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSubclass(Subclass subclass) {
        this.subclass = subclass;
    }

    public void setIsShow(IsShow isShow) {
        this.isShow = isShow;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public void setDisagree(int disagree) {
        this.disagree = disagree;
    }

}
