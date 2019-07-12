package com.jenkins.ssm1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jenkins.ssm1.dao.RoleRepository;
import com.jenkins.ssm1.domain.Role;

import java.util.List;

/**
 * Created by Nicky on 2017/12/2.
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    /**
     *
     * @param ids
     * @return
     */
    //@RedisCache
    public List<Role> findAll(List<Integer> ids){
        return roleRepository.findAll(ids);
    }
}
