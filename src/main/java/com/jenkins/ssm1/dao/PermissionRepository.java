package com.jenkins.ssm1.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jenkins.ssm1.domain.Permission;

/**
 * Created by Nicky on 2017/11/11.
 */

public interface PermissionRepository extends JpaRepository<Permission,Integer> {

}
