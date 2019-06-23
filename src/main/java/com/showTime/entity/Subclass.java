package com.showTime.entity;

import com.showTime.common.entity.IdEntity;
import com.showTime.common.tools.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "subclass")
public class Subclass extends IdEntity {
    private String subclassName;
    private Model model;        //0或1，0为儿童级别，1为成人级别,默认为儿童模式0

    public String getSubclassName() {
        return subclassName;
    }

    public Model getModel() {
        return model;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
