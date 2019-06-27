package com.showTime.dao;

import com.showTime.entity.Production;
import com.showTime.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, String>{
    @Query(value = "select user.type,user.idCard from User user  where user.account=?1 ")
     Object[][] getUserByAccount(String account);
    @Query(value = "select password,salt,face from user where account=?1  ",nativeQuery=true)
    User findPasswordByAccount(String account);
    boolean existsByIdCard(String idCard);
    boolean existsByPhone(String phone);
    boolean existsByMail(String mail);
    User findAllByPhone(String phone);
    User findAllByMail(String mail);
}
