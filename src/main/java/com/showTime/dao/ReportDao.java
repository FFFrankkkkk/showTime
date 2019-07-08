package com.showTime.dao;

import com.showTime.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao extends CrudRepository<Report,String> {

}
