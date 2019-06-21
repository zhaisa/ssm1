package com.jenkins.ssm1.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.jenkins.ssm1.domain.Role;

/**
 * Created by Nicky on 2017/7/30.
 */
public interface RolePageRepository extends PagingAndSortingRepository<Role, Integer> {

//    @Query("from Role r where r.roleId=:id")
//    Role findByRoleId(@Param("id") int id);
    @Query("from Role r where r.roleId=:id")
    Role findOne(int roleId);

}
