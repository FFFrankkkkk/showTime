package com.showTime.entity;

import com.showTime.common.entity.IdEntity;

import javax.persistence.*;

import com.showTime.common.tools.Model;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category extends IdEntity {
    private String categoryName;
//    private  Model   model;        //0或1，0为儿童级别，1为成人级别,默认为儿童模式0
    @OneToMany(mappedBy = "category",cascade= CascadeType.ALL)
    List<Production> productionList=new ArrayList<Production>();
    @OneToMany(mappedBy = "category",cascade= CascadeType.ALL,fetch= FetchType.EAGER)
    List<Subclass> subclasses =new ArrayList<Subclass>();
    public String getCategoryName() {
        return categoryName;
    }

    public List<Subclass> getSubclasses() {
        return subclasses;
    }

    public List<Production> getProductionList() {
        return productionList;
    }

//    public Model getModel() {
//        return model;
//    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }

    public void setSubclasses(List<Subclass> subclasses) {
        this.subclasses = subclasses;
    }

//    public void setModel(Model model) {
//        this.model = model;
//    }
}
