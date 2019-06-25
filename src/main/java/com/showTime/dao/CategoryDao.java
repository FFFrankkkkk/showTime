package com.showTime.dao;

import com.showTime.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends CrudRepository<Category,String> {
    @Query(value = "select * from Category where model=0 or model=?1",nativeQuery = true)
    List<Category> getCategoryByModel(String model);
    List<Category> findAllByModelOrModel(String mode11,String model2);
    boolean existsByCategoryName(String categoryName);
    Category findAllByCategoryName(String categoryName);
}
