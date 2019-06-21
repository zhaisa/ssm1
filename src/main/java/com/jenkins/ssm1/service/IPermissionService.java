package com.jenkins.ssm1.service;


import java.util.Set;

import com.jenkins.ssm1.domain.Permission;

/**
 * Created by Nicky on 2017/11/11.
 */

public interface IPermissionService {



    /**
     * 保存权限表信息
     *
     * @param permission
     */
    public void doSave(Permission permission);

    //@RedisCache
    public Set<Permission> findAllP();

    public Permission getOne(int id) ;

}