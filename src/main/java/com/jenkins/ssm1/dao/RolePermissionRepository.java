package com.jenkins.ssm1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jenkins.ssm1.domain.RolePermission;

import java.util.List;

/**
 * Created by Nicky on 2017/11/18.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,String> {

    @Query(value = "select rp from RolePermission rp where rp.roleId=:id")
    public List<RolePermission> findByRoleId(@Param("id") int id);

}
