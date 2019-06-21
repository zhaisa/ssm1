package com.jenkins.ssm1.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.jenkins.ssm1.domain.Permission;

/**
 * Created by Nicky on 2017/12/3.
 */
public interface PermissionPageRepository extends PagingAndSortingRepository<Permission,Integer> {

}
