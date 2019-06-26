package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.dao.CategoryDao;
import com.showTime.dao.SubclassDao;
import com.showTime.entity.Category;
import com.showTime.entity.Production;
import com.showTime.entity.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.showTime.dao.ProductionDao;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class ProductionService implements  IUserService, ICommonService<Production> {
    @Autowired
    private ProductionDao productionDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private SubclassDao subclassDao;
    public List<Production> getHotProduction(String type){
        return productionDao.getHotProduction(type);
    }
    public List<Production> getRecommendProduction(String type){
        return productionDao.getRecommendProduction(type);
    }

    @Override
    public void save(Production production) throws Exception {

    }
    public void save(Production production,String categoryId,String subclassId) throws Exception {
        if(subclassId!=null){
           Subclass subclass= subclassDao.findOne(subclassId);
           production.setSubclass(subclass);
        }else{
            Category category=categoryDao.findOne(categoryId);
            production.setCategory(category);
        }
        productionDao.save(production);
    }

    @Override
    public void update(Production production) throws Exception {

    }

    public void delete(Production production){

    }

    @Override
    public Production findById(Serializable id) throws Exception {
        return null;
    }

    @Override
    public <T> List<T> findByProperty(T c, String property, Object value) throws Exception {
        return null;
    }
    public boolean existsByTitle(String title){
        return productionDao.existsByTitle(title);
    }
}
