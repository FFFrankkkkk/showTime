package com.showTime.dao;

import com.showTime.entity.Subclass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubclassDao extends CrudRepository<Subclass,String> {
     boolean existsBySubclassName(String subclassName);
     Subclass findAllBySubclassName(String subclassName);

}
