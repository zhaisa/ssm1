package com.jenkins.ssm1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jenkins.ssm1.domain.RolePermission;

/**
 * Created by Nicky on 2017/12/2.
 */
public interface UserRoleRepository extends JpaRepository<RolePermission,String> {
}
