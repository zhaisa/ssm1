package com.jenkins.ssm1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jenkins.ssm1.dao.MenuTreeRepository;
import com.jenkins.ssm1.domain.Menu;

/**
 * Created by Nicky on 2017/6/17.
 */
@Service
public interface IMenuTreeService {



    /**
     * 查询所有的菜单
     * @return
     */
    @Transactional
    //@RedisCache
//    @LogService
    public List<Menu> findAll();

}
