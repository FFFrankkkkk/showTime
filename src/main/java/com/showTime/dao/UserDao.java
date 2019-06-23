package com.showTime.dao;

import com.showTime.entity.Production;
import com.showTime.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, String>{
    @Query(value = "select idCard from user where account=?1  LIMIT 1",nativeQuery=true)
     String getUserIdCardByAccount(String account);
    @Query(value = "select count(*) from user where idCard=?1  ",nativeQuery=true)
    int existsIdCard(String idCard);
    @Query(value = "select password,salt,face from user where account=?1  ",nativeQuery=true)
    User findPasswordByAccount(String account);
}
