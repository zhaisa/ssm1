package com.jenkins.ssm1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jenkins.ssm1.domain.RolePermission;

/**
 * Created by Nicky on 2017/12/2.
 */
@Mapper
@Repository
@Primary
public interface UserRoleRepository extends JpaRepository<RolePermission,String> {
}
