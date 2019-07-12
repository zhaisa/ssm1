package com.jenkins.ssm1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenkins.ssm1.domain.Menu;

/**
 * Created by Nicky on 2017/6/17.
 */

public interface MenuTreeRepository extends JpaRepository<Menu,Integer>{

}
