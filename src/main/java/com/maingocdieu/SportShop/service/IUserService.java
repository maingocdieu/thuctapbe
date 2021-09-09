package com.maingocdieu.SportShop.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maingocdieu.SportShop.dto.UserDto;
import com.maingocdieu.SportShop.entity.User;

public interface IUserService {
//  List<User> findByUsername(String userName);

  User updateUser(long id, UserDto user);

  Boolean deleteUserById(Long id);

  UserDto insertUser(UserDto userDto);

  List<User> findAllUser();
  
  User findByUsernameAndPassword(String userName,String password );

  User findById(Long id);
  


  
  Page<User> findAll(UserDto productDto);
  
User updateUserAdmin(Long id ,UserDto data);
  



}
