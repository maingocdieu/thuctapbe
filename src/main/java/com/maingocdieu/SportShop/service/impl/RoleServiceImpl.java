package com.maingocdieu.SportShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.entity.Role;
import com.maingocdieu.SportShop.repository.RoleRepository;
import com.maingocdieu.SportShop.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
  @Autowired
  RoleRepository roleRepository;

  @Override
  public List<Role> findAllRole() {
   return roleRepository.findAll();
  }
  

}
