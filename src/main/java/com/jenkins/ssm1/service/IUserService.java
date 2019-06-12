package com.jenkins.ssm1.service;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jenkins.ssm1.domain.User;

public interface IUserService {
	int saveIP(Map<String,String> map);
	Set<String> getRoles(String username);
	Set<String> getPermissions(String username);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
	User findById(int id);
	User doLoginCheck(String username,String password);
	User findByUId(int id);
	PageRequest buildPageRequest(int num, int size, Sort.Direction asc,String string) ;
	Page<User> findAll(int pageNo, int pageSize, Sort.Direction dir, String str);
	Page<User> searchU(int pageNo, int pageSize, Sort.Direction dir, String str,String keyword,Date startDate,Date endDate);
	void saveU(User user);
	int updateU(User user);
	int updateLoginIpById( String loginIp,  String username);
	int updatePasswordById(String password, String username);
}
