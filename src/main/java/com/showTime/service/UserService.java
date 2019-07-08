package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.dao.FollowDao;
import com.showTime.dao.UserDao;
import com.showTime.entity.Follow;
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
    @Autowired
    private FollowDao followDao;
    public UserService(){
        System.out.println("UserService init");
    }
    @Override
    public void save(User user) {
            userDao.save(user);
    }

    @Override
    public void update(User user) throws Exception {
          userDao.save(user);
    }

    @Override
    public void delete(User user) throws Exception {

    }

    @Override
    public User findById(Serializable id) throws Exception {
        return userDao.findAllByAccount(id.toString());

    }
    public User findByAccount(String account) throws Exception {
        return userDao.findAllByAccount(account);

    }
    @Override
    public <T> List<T> findByProperty(T c, String property, Object value) throws Exception {
        return null;
    }

    public boolean exists(String account){
        return userDao.exists(account);
    }

    public User findPasswordByAccount(String account){
        return userDao.findPasswordByAccount(account);
    }
    public boolean existsByIdCard(String idCard){
        return userDao.existsByIdCard(idCard);
    }
    public boolean existsByPhone(String phone){
        return userDao.existsByIdCard(phone);
    }
    public boolean existsByMail(String mail){
        return userDao.existsByMail(mail);
    }
    public User findAllByPhone(String phone){
        return userDao.findAllByPhone(phone);
    }
    public User findAllByMail(String mail){
        return  userDao.findAllByMail(mail);
    }
    public    Object[][]  getUserByAccount(String account){
        return userDao.getUserByAccount(account);
    }
    public void saveFollow(Follow follow){
        followDao.save(follow);
    }
    public  boolean existsByAccountAndFollowAccount(String account,String followAcount){
        return followDao.existsByAccountAndFollowAccount(account,followAcount);
    }
   public int countAllByFollowAccount(String followAccount){
       return followDao.countAllByFollowAccount(followAccount);
   }
   public  Follow findAllByAccountAndFollowAccount(String account,String followAcount){
        return followDao.findAllByAccountAndFollowAccount(account,followAcount);
   }
   public void deleteFollow(Follow follow){
        followDao.delete(follow);
   }
   public  List<Follow>  findAllByAccount(String account){
        return followDao.findAllByAccount(account);
   }
   public List<Follow>  findAllByFollowAccount(String followAccount){
        return followDao.findAllByFollowAccount(followAccount);
   }
}
