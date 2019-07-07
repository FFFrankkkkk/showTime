package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.dao.BlackListDao;
import com.showTime.entity.BlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
//
@Service
@Transactional
public class BlackListService implements IBlackListService  {

    @Autowired
    private BlackListDao blackListDao;
    public BlackListService(){
        System.out.println("BlackListService");
    }

    @Override
    public void save(BlackList blackList) {
        blackListDao.save(blackList);
    }
    public   List<BlackList> findAll(){
        return (List<BlackList>) blackListDao.findAll();
    }
}
