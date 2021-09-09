package com.maingocdieu.SportShop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.UserConverter;
import com.maingocdieu.SportShop.dto.UserDto;
import com.maingocdieu.SportShop.entity.ERole;
import com.maingocdieu.SportShop.entity.Role;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.repository.RoleRepository;
import com.maingocdieu.SportShop.repository.UserRepository;
import com.maingocdieu.SportShop.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  UserRepository userReponsitory;
  
  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  private UserConverter userConverter;


  @Override
  public User updateUser(long id, UserDto userEntity) {
    Optional<User> user = userReponsitory.findById(id);
    if (user.isPresent()) {
      User updateUser = user.get();
      updateUser.setFullName(userEntity.getFullName());
      updateUser.setEmail(userEntity.getEmail());
      updateUser.setAddress(userEntity.getAddress());
      updateUser.setPhone(userEntity.getPhone());
      userReponsitory.save(updateUser);
      return updateUser;
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteUserById(Long id) {
	  User  user = userReponsitory.findById(id).get();
	  if(user.getOrders().size() > 0) {
		  return false;
	  } else {
		  userReponsitory.deleteById(id);
		  return true;
	  }
    
  }


  @Override
  public List<User> findAllUser() {
    return userReponsitory.findAll();
  }

  @Override
  public User findByUsernameAndPassword(String userName, String password) {
    return userReponsitory.findByUsernameAndPassword(userName, password);
  }

  @Override
  public UserDto insertUser(UserDto userDto) {
    User user = userConverter.converToEntity(userDto);
    userReponsitory.save(user);
    return userDto;
  }

  @Override
  public User findById(Long id) {
    Optional<User> user = userReponsitory.findById(id);
    if (user.isPresent()) {
      return user.get();
    } else {
      return null;
    }
  }

 





@Override
public Page<User> findAll(UserDto userDto) {
	Pageable pageable = PageRequest.of(userDto.getPage(), 10);
	return  userReponsitory.findAll(pageable);
}

@Override
public User updateUserAdmin(Long id, UserDto data) {
	Optional<User> user = userReponsitory.findById(id);
    if (user.isPresent()) {
      User updateUser = user.get();
      updateUser.setFullName(data.getFullName());
      updateUser.setEmail(data.getEmail());
      updateUser.setAddress(data.getAddress());
      updateUser.setPhone(data.getPhone());
      if(data.getRole() != "-1") {
    	  Role role;
    	  Set<Role> roles = new HashSet<>();
    	  if(data.getRole().equals("ROLE_ADMIN")) {
    		  role = roleRepository.findByName(ERole.ROLE_ADMIN).get();
    	  } else {
    		  role = roleRepository.findByName(ERole.ROLE_USER).get();
    	  }
    	  roles.add(role);
    	  updateUser.setRoles(roles);
      }
      userReponsitory.save(updateUser);
      return updateUser;
    } else {
      return null;
    }
	
}





 
}


