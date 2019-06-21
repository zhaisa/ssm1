package com.jenkins.ssm1.service;

import java.util.List;

import com.jenkins.ssm1.domain.Role;

/**
 * Created by Nicky on 2017/12/2.
 */

public interface IRoleService {

//    @Autowired
//    RoleRepository roleRepository;

    /**
     *
     * @param ids
     * @return
     */
    //@RedisCache
    public List<Role> findAll(List<Integer> ids);
}
