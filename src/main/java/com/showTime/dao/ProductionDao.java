package com.showTime.dao;

import com.showTime.common.tools.IsShow;
import com.showTime.common.tools.Model;
import com.showTime.common.tools.Recommend;
import com.showTime.entity.Production;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductionDao extends CrudRepository<Production,String> {
    @Query(value = "select id,title,address,img from production where model=?1 order by account DESC",nativeQuery=true)
    List<Production> getHotProduction(String type);
    @Query(value = "select * from production  where recommend=1 and model=?1",nativeQuery=true)
    List<Production> getRecommendProduction(String type);
    List<Production> findAllByRecommendAndModelAndIsShow(Recommend recommend, Model model, IsShow isShow);
    List<Production> findAllByRecommendAndModelAndIsShowAndCategoryId(Recommend recommend, Model model, IsShow isShow,String categoryId);
    List<Production> findAllByModelAndIsShowOrderByAccountDesc(Model model, IsShow isShow);
    List<Production> findAllByModelAndIsShowAndCategoryIdOrderByAccountDesc(Model model, IsShow isShow,String categoryId);
    List<Production> findAllByCategoryIdAndModelAndIsShow(String categoryId,Model model, IsShow isShow);
    List<Production> findAllByIdAndModelAndIsShow(String id,Model model, IsShow isShow);
    List<Production> findAllBySubclassIdAndModelAndIsShow(String subclassId,Model model, IsShow isShow);
    List<Production> findAllByTitleLikeOrContextLikeAndModelAndIsShow(String search,String search1,Model model, IsShow isShow);
    List<Production> findAllByCheckTimeIsBetween(Date startTime, Date endTime);
//    List<Production> findAllByche(String startTime,String endTime);
    Production findAllByAddress(String address);
    void  deleteAllBySubclass(String subclass);
    boolean  existsByTitle(String title);
}
