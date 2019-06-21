package com.jenkins.ssm1.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jenkins.ssm1.domain.Permission;

/**
 * Created by Nicky on 2017/12/3.
 */
@Mapper
@Repository
@Primary
public interface PermissionPageRepository extends PagingAndSortingRepository<Permission,Integer> {

}
