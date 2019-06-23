package com.showTime.entity;

import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.IsShow;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="favorite")
public class Favorite extends IdEntity {
    private String favoriteName;
    private IsShow isShow;      //是否公开，公开：1，不公开：0;
    @JoinColumn(name="userAccount",nullable=false)
    @ManyToOne(fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private User   user;      //用户Id,pk
//    @OneToMany(mappedBy = "favorite")
//    private List<Production> productions;
//
//    public List<Production> getProductions() {
//        return productions;
//    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public IsShow getIsShow() {
        return isShow;
    }

    public User getUser() {
        return user;
    }

//    public void setProductions(List<Production> productions) {
//        this.productions = productions;
//    }

    public void setIsShow(IsShow isShow) {
        this.isShow = isShow;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

}
