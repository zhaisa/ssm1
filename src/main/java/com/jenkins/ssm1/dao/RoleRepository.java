package com.jenkins.ssm1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jenkins.ssm1.domain.Role;

/**
 * Created by Nicky on 2017/12/2.
 */

public interface RoleRepository extends JpaRepository<Role,Integer> {
 List<Role> findAll(List<Integer> ids);
}
