package com.showTime.dao;

import com.showTime.entity.Follow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDao extends CrudRepository<Follow,String> {
    boolean existsByAccountAndFollowAccount(String account,String followAcount);
    int countAllByFollowAccount(String followAccount);
    Follow findAllByAccountAndFollowAccount(String account,String followAcount);
    List<Follow>  findAllByAccount(String account);
    List<Follow>  findAllByFollowAccount(String followAccount);
}
