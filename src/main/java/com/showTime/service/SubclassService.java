package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.dao.ProductionDao;
import com.showTime.dao.SubclassDao;
import com.showTime.entity.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class SubclassService  implements IUserService, ICommonService<Subclass> {
    @Autowired
    SubclassDao subclassDao;
    @Autowired
    ProductionDao productionDao;
    @Override
    public void save(Subclass subclass) throws Exception {
         subclassDao.save(subclass);
    }

    @Override
    public void update(Subclass subclass) throws Exception {

    }

    @Override
    public void delete(Subclass subclass) throws Exception {
        subclassDao.delete(subclass);
    }
    public void delete(String subclassName) throws Exception {
        subclassDao.delete(subclassName);
    }
    @Override
    public Subclass findById(Serializable id) throws Exception {
        return null;
    }

    @Override
    public <T> List<T> findByProperty(T c, String property, Object value) throws Exception {
        return null;
    }
    public boolean existsBySubclassName(String subclassName){
           return subclassDao.existsBySubclassName(subclassName);
    }
    public Subclass findAllBySubclassName(String subclassName){
        return subclassDao.findAllBySubclassName(subclassName);
    }


}
