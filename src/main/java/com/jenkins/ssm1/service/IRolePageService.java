package com.jenkins.ssm1.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jenkins.ssm1.domain.Role;

/**
 * Created by Nicky on 2017/7/30.
 */

public interface IRolePageService {

   

    /**
     * 构建PageRequest对象
     * @param num
     * @param size
     * @param asc
     * @param string
     * @return
     */
 PageRequest buildPageRequest(int num, int size, Sort.Direction asc,
                                         String string);

    /**
     * 获取所有的菜单信息并分页显示
     * @param pageNo
     * 			当前页面数
     * @param pageSize
     * 			每一页面的页数
     * @return
     */
    public Page<Role> findAll(int pageNo, int pageSize, Sort.Direction dir, String str);
    public List<Role> findAllRole();

    /**
     * 根据角色id查找角色信息
     * @param roleId
     * @return
     */
    public Role findByRoleId(String roleId);
    /**
     * 保存角色信息
     * @param role
     */
    public void doSave(Role role);



}
