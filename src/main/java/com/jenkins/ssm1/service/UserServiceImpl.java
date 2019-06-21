package com.jenkins.ssm1.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jenkins.ssm1.dao.UserDao;
import com.jenkins.ssm1.domain.Operation;
import com.jenkins.ssm1.domain.Permission;
import com.jenkins.ssm1.domain.Role;
import com.jenkins.ssm1.domain.User;
@Transactional
@Service
public class UserServiceImpl implements IUserService {
	
	UserDao userRepository;

	@Override
	public int saveIP(Map<String, String> map) {
		String loginIp = map.get("loginIp");
		String username = map.get("username");
		int code = userRepository.updateLoginIpById(loginIp, username);
		return code;
	}

	@Override
	// @RedisCache(nameSpace = RedisCacheNamespace.SYS_USER)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	// @RedisCache(nameSpace = RedisCacheNamespace.SYS_USER)
	// @RedisCache
	public User doLoginCheck(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	// @RedisCache(nameSpace = RedisCacheNamespace.SYS_USER)
	public User findByUId(int id) {
		return userRepository.findById(id);
	}

	/**
	 * 构建PageRequest对象
	 * 
	 * @param num
	 * @param size
	 * @param asc
	 * @param string
	 * @return
	 */
	@Override
	public PageRequest buildPageRequest(int num, int size, Direction asc, String string) {
		// TODO Auto-generated method stub
		return new PageRequest(num - 1, size, null, string);
	}

	/**
	 * 获取所有的用户信息并分页显示
	 * 
	 * @param pageNo
	 *            当前页面数
	 * @param pageSize
	 *            每一页面的页数
	 * @return
	 */
	@Override
	public Page<User> findAll(int pageNo, int pageSize, Sort.Direction dir, String str) {
		PageRequest request = buildPageRequest(pageNo, pageSize, dir, str);
		Page<User> users = userRepository.findAll(request);
		return users;
	}

	/**
	 * 根据关键字和日期查询
	 * 
	 * @param pageNo
	 *            当前页面数
	 * @param pageSize
	 *            每一页面的页数
	 * @param dir
	 * @param str
	 * @param keyword
	 *            关键字
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public Page<User> searchU(int pageNo, int pageSize, Sort.Direction dir, String str, String keyword, Date startDate,
			Date endDate) {
		PageRequest request = buildPageRequest(pageNo, pageSize, dir, str);
		Page<User> users = userRepository.searchU(startDate, endDate, request);
		return users;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	@Override
	public void saveU(User user) {
		userRepository.save(user);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	@Override
	public int updateU(User user) {
		return userRepository.updatePasswordById(user.getPassword(), user.getUsername());
	}

	@Override
	public Set<String> getRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getPermissions(String username) {
		User user = userRepository.findByUsername(username);
		Set<Role> roles = user.getRoles();
		//创建一个HashSet来存放角色权限信息
		Set<String> permissionStrs = new HashSet<String>();
		for(Role r:roles){
			for(Permission p:r.getPermissions())
				for(Operation ope:p.getOperations()){
					permissionStrs.add(ope.getOperation());
				}
		}
		return permissionStrs;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public int updateLoginIpById(String loginIp, String username) {
		// TODO Auto-generated method stub
		return userRepository.updateLoginIpById(loginIp, username);
	}

	@Override
	public int updatePasswordById(String password, String username) {
		// TODO Auto-generated method stub
		return userRepository.updatePasswordById(password, username);
	}

}
