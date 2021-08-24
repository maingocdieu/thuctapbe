
package com.maingocdieu.SportShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maingocdieu.SportShop.dto.UserDto;
import com.maingocdieu.SportShop.entity.Product;
import com.maingocdieu.SportShop.entity.User;
import com.maingocdieu.SportShop.repository.UserRepository;
import com.maingocdieu.SportShop.service.impl.UserServiceImpl;



@RestController
public class UserRestController {

  @Autowired
  UserServiceImpl userService;

  @Autowired
  UserRepository userReponsitory;


  
  @GetMapping("/api/employees/{id}")
  public ResponseEntity<?> getUser(@PathVariable("id") long id) {
    User currentUser = userService.findById(id);
    if (currentUser == null) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(currentUser);
  }

  @PostMapping("/api/employees")
  public ResponseEntity<Page<User>> insertUser(@RequestBody UserDto userDto) {
	  Page<User> response = userService.findAll(userDto);
	  return new ResponseEntity<Page<User>>(response, HttpStatus.OK);
  }

  @PostMapping("/api/employees/delete")
  public ResponseEntity<User> deleteUser(@RequestBody List<Long> ids) {
    Boolean temp = userService.deleteUserById(ids);
    if (Boolean.TRUE.equals(temp)) {
      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/api/employees/{id}")
  public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserDto userEntity) {
    User currentUser = userService.updateUser(id, userEntity);
    if (currentUser == null) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(true);
  }

  @GetMapping("/api/employees")
  public List<User> getAll() {
    return userService.findAllUser();
  }


}

