package com.showTime.dao;

import com.showTime.entity.Production;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionDao extends CrudRepository<Production,String> {
    @Query(value = "select id,title,address,img from production where model=?1 order by account DESC",nativeQuery=true)
    List<Production> getHotProduction(String type);
    @Query(value = "select * from production  where recommend=1 and model=?1",nativeQuery=true)
    List<Production> getRecommendProduction(String type);
    List<Production> findAllByRecommendAndModel(String recommend,String model);
    void  deleteAllBySubclass(String subclass);
    boolean  existsByTitle(String title);
}
