package com.jenkins.ssm1.dao;


import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jenkins.ssm1.domain.User;
@Mapper
@Repository
@Primary
public interface UserDao extends PagingAndSortingRepository<User, Integer> {
	@Select ("username from User where username =#{username}")
	User findByUsername(String username);

	@Select("select * from User u where u.username=#{username} and u.password=#{password}")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Select("select * from User u where u.id=#{id}")
	User findById(@Param("id") int id);

	@Select("select * from User u where u.lastLogin BETWEEN #{startDate} and #{endDate}")
	Page<User> searchU(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

	
	@Update("update User u set u.loginIp =#{loginIp} where u.username = #{username}")
	int updateLoginIpById( String loginIp,  String username);

	@Update("update User u set u.password =#{password} where u.username = #{username}")
	int updatePasswordById(String password, String username);
}
