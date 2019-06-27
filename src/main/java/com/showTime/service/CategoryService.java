package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.dao.CategoryDao;
import com.showTime.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class CategoryService  implements IUserService, ICommonService<Category> {
    @Autowired
    CategoryDao categoryDao;
    public List<Category> findAll(){
        return (List<Category>) categoryDao.findAll();
    }
    public boolean existsByCategoryName(String categoryName){
        return categoryDao.existsByCategoryName(categoryName);
    }

    @Override
    public void save(Category category) throws Exception {
        categoryDao.save(category);
    }

    @Override
    public void update(Category category) throws Exception {

    }

    @Override
    public void delete(Category category) throws Exception {

    }
    public void delete(String categoryName) throws Exception {
           categoryDao.delete(categoryName);
    }
    @Override
    public Category findById(Serializable id) throws Exception {
        return null;
    }

    @Override
    public <T> List<T> findByProperty(T c, String property, Object value) throws Exception {
        return null;
    }
    public  Category findAllByCategoryName(String categoryName){
        return categoryDao.findAllByCategoryName(categoryName);
    }
}
