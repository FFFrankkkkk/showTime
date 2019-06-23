package com.showTime.common.service;

import java.io.Serializable;
import java.util.List;

public interface ICommonService <T>{
    void save(T t) throws Exception;
//    void save(List<T> t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;
//    void delete(Class<?> c, Serializable... ids) throws Exception;
      T findById(Serializable id) throws Exception;
//    <T> Paging<T> find(Class<T> c, int page, int rows) throws Exception;
//    <T> Paging<T> find(String hql, int page, int rows, Object... params) throws Exception;
//    <T> Paging<T> findByProperty(Class<T> c, String property, Object value, int page, int rows)throws Exception;
      <T> List<T> findByProperty(T c, String property, Object value)throws Exception;
//    <T> T findOne(String hql, Object... params) throws Exception;
//    <T> T findOneByProperty(Class<T> c, String property, Object value)throws Exception;

//    public abstract <T> List<T> list(String hql,int page,int rows,Object... params)throws Exception;
//    public abstract <T> List<T> list(Class<T> c,int page,int rows)throws Exception;
//    public abstract <T> List<T> list(Class<T> c)throws Exception;
}
