package com.maingocdieu.SportShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maingocdieu.SportShop.converter.UserConverter;
import com.maingocdieu.SportShop.dto.ProductDto;
import com.maingocdieu.SportShop.dto.UserDto;
import com.maingocdieu.SportShop.entity.Product;
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

//  @Override
//  public List<User> findByUsername(String userName) {
//    return userReponsitory.findByUsername(userName);
//  }

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
  public Boolean deleteUserById(List<Long> id) {
    for (long l : id) {
      userReponsitory.deleteById(l);
    }
    return true;
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

//  @Override
//  public List<User> findByPagingCriteria(String userName, Pageable pageable) {
//    Page<User> page = userReponsitory.findAll(new Specification<User>() {
//      private static final long serialVersionUID = 1L;
//      @Override
//      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicates = new ArrayList<>();
//        if (userName != null) {
//          predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userName"), userName)));
//        }
//        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//      }
//
//    }, pageable);
//    page.getTotalElements();
//    page.getTotalPages();
//    return page.getContent();
//  }

//  @Override
//  public List<User> findByCriteria(UserDto userDto) {
//    if (userDto.getIdRole() == null || userDto.getIdRole().isEmpty() || userDto.getIdRole().get(0) == null) {
//      List<Long> codes = new ArrayList<Long>();
//      userDto.setIdRole(codes);  
//      userDto.getIdRole().add(-1L);
//    }
//    return userReponsitory.findAllByCriteria(userDto.getUserName());
//  }

  @Override
  public Page<User> findPageUser(UserDto userDto) {
   Pageable firstPageWithTwoElements = PageRequest.of(userDto.getPage(), 3);
  return userReponsitory.findAllUserWithPagination(userDto.getUsername(), userDto.getIdRole(), userDto.getEmail(), userDto.getAddress(),firstPageWithTwoElements);
  }


@Override
public List<User> findByPagingCriteria(String userName, Pageable pageable) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<User> findByCriteria(UserDto userDto) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<User> findAll(UserDto userDto) {
	Pageable pageable = PageRequest.of(userDto.getPage(), 10);
	return  userReponsitory.findAll(pageable);
}
 
}


