package com.showTime.service;

import com.showTime.common.service.ICommonService;
import com.showTime.common.tools.IsShow;
import com.showTime.common.tools.Model;
import com.showTime.common.tools.Recommend;
import com.showTime.dao.CategoryDao;
import com.showTime.dao.ReportDao;
import com.showTime.dao.SubclassDao;
import com.showTime.entity.Category;
import com.showTime.entity.Production;
import com.showTime.entity.Report;
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
    @Autowired
    private ReportDao reportDao;
    public List<Production> getHotProduction(String type){
        return productionDao.getHotProduction(type);
    }
    public List<Production> getRecommendProduction(String type){
        return productionDao.getRecommendProduction(type);
    }

    @Override
    public void save(Production production) throws Exception {
             productionDao.save(production);
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
   public List<Production> findAllByRecommendAndModelAndIsShow(Recommend recommend, Model model,IsShow isShow,String categoryId){
        if(categoryId==null||"".equals(categoryId)) {
            return productionDao.findAllByRecommendAndModelAndIsShow(recommend, model, isShow);
        }else{
            return productionDao.findAllByRecommendAndModelAndIsShowAndCategoryId(recommend, model, isShow,categoryId);
        }
//       return productionDao.findAllByRecommendAndModelAndIsShow(recommend, model, isShow);

   }
   public  List<Production> findAllByModelAndIsShowOrderByAccountDesc(Model model, IsShow isShow,String categoryId) {
       if (categoryId == null || "".equals(categoryId)) {
           return productionDao.findAllByModelAndIsShowOrderByAccountDesc(model, isShow);
       }else{
           return productionDao.findAllByModelAndIsShowAndCategoryIdOrderByAccountDesc(model, isShow,categoryId);
       }
//       return productionDao.findAllByModelAndIsShowOrderByAccountDesc(model, isShow);
   }
    public  List<Production> findAllByCategoryIdAndModelAndIsShow(String categoryId,Model model, IsShow isShow){
        return productionDao.findAllByCategoryIdAndModelAndIsShow(categoryId,model,isShow);
    }
    public  List<Production> findAllByIdAndModelAndIsShow(String id,Model model, IsShow isShow){
        return productionDao.findAllByIdAndModelAndIsShow(id,model,isShow);
    }
    public  List<Production> findAllBySubclassIdAndModelAndIsShow(String subclassId,Model model, IsShow isShow){
        return productionDao.findAllBySubclassIdAndModelAndIsShow(subclassId,model,isShow);
    }
    public  Production findAllByAddress(String address){
        return productionDao.findAllByAddress(address);
    }
    public   List<Production> findAllByTitleLikeOrContextLikeAndModelAndIsShow(String search,Model model, IsShow isShow){
        return productionDao.findAllByTitleLikeOrContextLikeAndModelAndIsShow( search,search,model,  isShow);
    }
    public void saveReport(Report report){
        reportDao.save(report);
    }
}
