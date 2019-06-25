package com.showTime.entity;

import com.showTime.common.tools.Sex;
import com.showTime.dao.*;
import com.showTime.service.IBlackListService;
import com.showTime.service.IUserService;
import com.showTime.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class test extends BaseTest{
     @Autowired
    SubclassDao subclassDao;
    @Test
    @Rollback(value = false)
    public void testUB() throws Exception {
        subclassDao.delete("12");
//        User user=new User();
//        user.setAccount("123456");
//        user.setUserName("77777");
//        user.setPassword("123456");
//        user.setSex(Sex.FEMALE);
//        userService.save(user);
//        User user1=user;
//        user1.setAccount("123456");
//        user1.setPassword("11111111");
//        user1.setUserName("123123");
//           BlackList blackList =  blackListDao.findById("297ead306aee257c016aee2583de0000").orElse(null);
//           System.out.println(blackList.toString());
//        BlackList blackList=new BlackList();
//        blackList.setUser(user);
//        blackListService.save(blackList);
    }
}
