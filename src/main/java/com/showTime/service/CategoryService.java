package com.showTime.service;

import com.showTime.dao.CategoryDao;
import com.showTime.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;
    public List<Category> getCategoryByModel(String model){
        return categoryDao.getCategoryByModel(model);
    }
}
