package com.showTime.dao;

import com.showTime.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends CrudRepository<Category,String> {
    boolean existsByCategoryName(String categoryName);
    Category findAllByCategoryName(String categoryName);
}
