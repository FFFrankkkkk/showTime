package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="production_favorite")
public class production_favorite  extends IdEntity {

    private int productionId;//作品Id,pk
    private int favoriteId;//收藏夹Id,pk

}
