package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;
import java.util.List;

//@Entity
//@Table(name="follow")
public class Follow extends IdEntity {
    @OneToOne(fetch= FetchType.EAGER)
    private User    user;       //用户id，外键，关联user表
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name="follow_users",  joinColumns = {@JoinColumn(name = "follow_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName="account")})
    private List<User> users;

    public User getUser() {
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
