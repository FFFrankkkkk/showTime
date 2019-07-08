package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;
import java.util.List;
//
@Entity
@Table(name="user_follow")
public class Follow extends IdEntity {//关注表
//     @OneToOne(fetch= FetchType.EAGER)
//     @ManyToOne(fetch= FetchType.EAGER)
//     private User    user;       //用户id，外键，关联user表
//    @OneToMany(fetch=FetchType.EAGER)
//    @JoinTable(name="follow_users",
//            joinColumns = {@JoinColumn(name = "follow_id",referencedColumnName="id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName="account")})
//    private List<User> users;
//    @OneToOne(fetch= FetchType.EAGER)
//     @JoinColumn(name = "followUser_account",referencedColumnName="account")
//    private User    followUser;
//    public User getUser() {
//        return user;
//    }

//    public User getFollowUser() {
//        return followUser;
//    }
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

//    public void setFollowUser(User followUser) {
//        this.followUser = followUser;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }
    String account;
    String followAccount;

    public String getAccount() {
        return account;
    }

    public String getFollowAccount() {
        return followAccount;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setFollowAccount(String followAccount) {
        this.followAccount = followAccount;
    }
}
