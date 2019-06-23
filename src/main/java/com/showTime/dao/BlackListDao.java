package com.showTime.dao;

import com.showTime.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//@Component
@Repository
public interface BlackListDao extends CrudRepository<BlackList,String>{
}
