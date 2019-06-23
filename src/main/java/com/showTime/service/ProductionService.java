package com.showTime.service;

import com.showTime.entity.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.showTime.dao.ProductionDao;
import java.util.List;

@Service
@Transactional
public class ProductionService {
    @Autowired
    private ProductionDao productionDao;
    public List<Production> getHotProduction(String type){
        return productionDao.getHotProduction(type);
    }
    public List<Production> getRecommendProduction(String type){
        return productionDao.getRecommendProduction(type);
    }
}
