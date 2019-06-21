package com.jenkins.ssm1.service;

import java.util.List;

import com.jenkins.ssm1.domain.RolePermission;

/**
 * Created by Nicky on 2017/11/18.
 */

public interface IRolePermissionService {

//    @Autowired
//    RolePermissionRepository rolePermissionRepository;

    /**
     * 保存数据
     * @param rp
     * @return
     */
    public boolean doSave(RolePermission rp);

    /**
     * 删除数据
     * @param rp
     * @return
     */
    public boolean doDel(RolePermission rp);

    /**
     * 通过角色Id获取数据
     * @param roleId
     * @return
     */
    public List<RolePermission> findById(int roleId);


}
