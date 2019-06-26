package com.showTime.entity;

import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subclass")//音乐分类下的子分类
public class Subclass extends IdEntity {
    @ManyToOne(fetch= FetchType.EAGER)
    private Category category;
    private String subclassName;
//    private Model model;        //0或1，0为儿童级别，1为成人级别,默认为儿童模式0
    @OneToMany(mappedBy = "subclass",cascade= CascadeType.ALL)
    List<Production> productionList=new ArrayList<Production>();
    public Category getCategory() {
        return category;
    }

    public List<Production> getProductionList() {
        return productionList;
    }

    public String getSubclassName() {
        return subclassName;
    }

//    public Model getModel() {
//        return model;
//    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }

//    public void setModel(Model model) {
//        this.model = model;
//    }
}
