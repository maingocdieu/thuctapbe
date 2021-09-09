package com.maingocdieu.SportShop.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.entity.Role;
import com.maingocdieu.SportShop.service.IRoleService;


@RestController
@RequestMapping("/api/role")
public class RoleRestController {
  @Autowired
  IRoleService roleService;
  
  @GetMapping()
  public ResponseEntity<?> getRoles() {
    List<Role> listAllRole = roleService.findAllRole();
    return ResponseEntity.ok(listAllRole);
  }
  
}
