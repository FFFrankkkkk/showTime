package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.dao.UserDao;
import com.showTime.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService, ICommonService<User> {

    @Autowired
    private UserDao userDao;

    public UserService(){
        System.out.println("UserService init");
    }
    @Override
    public void save(User user) {
            userDao.save(user);
    }

    @Override
    public void update(User user) throws Exception {

    }

    @Override
    public void delete(User user) throws Exception {

    }

    @Override
    public User findById(Serializable id) throws Exception {
        return userDao.findOne(id.toString());
    }

    @Override
    public <T> List<T> findByProperty(T c, String property, Object value) throws Exception {
        return null;
    }

    public boolean exists(String account){
        return userDao.exists(account);
    }

    public int existsIdCard(String IdCard){
        return userDao.existsIdCard(IdCard);
    }
    public User findPasswordByAccount(String account){
        return userDao.findPasswordByAccount(account);
    }
}
