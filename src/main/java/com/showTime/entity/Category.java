package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.showTime.common.tools.Model;
@Entity
@Table(name="category")
public class Category extends IdEntity {
    private String categoryName;
    private  Model   model;        //0或1，0为儿童级别，1为成人级别,默认为儿童模式0

    public String getCategoryName() {
        return categoryName;
    }

    public Model getModel() {
        return model;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
