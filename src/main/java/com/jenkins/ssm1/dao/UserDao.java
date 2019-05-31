package com.jenkins.ssm1.dao;


import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jenkins.ssm1.domain.User;
@Mapper
@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);

	@Select(value = "select u from User u where u.username=:username and u.password=:password")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Select( value = "select u from User u where u.id=:id")
	User findById(@Param("id") int id);

	@Select(value = "select u from User u where u.lastLogin BETWEEN :startDate and :endDate")
	Page<User> searchU(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

	
	@Update(value = "update User u set u.loginIp =?1 where u.username = ?2")
	int updateLoginIpById( String loginIp,  String username);

	@Update(value = "update User u set u.password =?1 where u.username = ?2")
	int updatePasswordById(String password, String username);
}
