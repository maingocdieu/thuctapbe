package com.maingocdieu.SportShop.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maingocdieu.SportShop.dto.UserDto;
import com.maingocdieu.SportShop.entity.User;

public interface IUserService {
//  List<User> findByUsername(String userName);

  User updateUser(long id, UserDto user);

  Boolean deleteUserById(List<Long> ids);

  UserDto insertUser(UserDto userDto);

  List<User> findAllUser();
  
  User findByUsernameAndPassword(String userName,String password );

  User findById(Long id);
  
  public List<User> findByPagingCriteria(String userName,Pageable pageable);
  
  public List<User> findByCriteria(UserDto userDto);
  
  public Page<User> findPageUser(UserDto userDto);
  
  Page<User> findAll(UserDto productDto);
  
  
  



}
