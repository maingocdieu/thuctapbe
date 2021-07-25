//
//package com.example.shop.demo.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.shop.demo.dto.UserDto;
//import com.example.shop.demo.entity.User;
//import com.example.shop.demo.repository.UserRepository;
//import com.example.shop.demo.service.impl.UserServiceImpl;
//
//
//
//@RestController
//public class UserRestController {
//
//  @Autowired
//  UserServiceImpl userService;
//
//  @Autowired
//  UserRepository userReponsitory;
//
////  @GetMapping("/employees/{username}")
////  public List<User> getAllUser(@PathVariable("username") String username) {
////    return userService.findByUsername(username);
////  }
//  
//  @GetMapping("/api/employees/{id}")
//  public ResponseEntity<?> getUser(@PathVariable("id") long id) {
//    User currentUser = userService.findById(id);
//    if (currentUser == null) {
//      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//    }
//    return ResponseEntity.ok(currentUser);
//  }
//
//  @PostMapping("/api/employees")
//  public ResponseEntity<UserDto> insertUser(@RequestBody UserDto userDto) {
//     userService.insertUser(userDto);
//    return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
//  }
//
//  @PostMapping("/api/employees/delete")
//  public ResponseEntity<User> deleteUser(@RequestBody List<Long> ids) {
//    Boolean temp = userService.deleteUserById(ids);
//    if (Boolean.TRUE.equals(temp)) {
//      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
//    return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//  }
//
//  @PutMapping("/api/employees/{id}")
//  public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User userEntity) {
//    User currentUser = userService.updateUser(id, userEntity);
//    if (currentUser == null) {
//      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//    }
//    return ResponseEntity.ok(true);
//  }
//
//  @GetMapping("/api/employees")
//  public List<User> getAll() {
//    return userService.findAllUser();
//  }
//
//  @PostMapping("/api/employees/login")
//  public ResponseEntity<User> login(@RequestBody User user) {
//    User userReponse =
//        userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    return new ResponseEntity<User>(userReponse, HttpStatus.OK);
//  }
//  
//  @PostMapping("api/employees/username")
//  public ResponseEntity<List<User>> finByUserName(@RequestBody UserDto userDto) {
//    List<User> response = userService.findByCriteria(userDto);
//    return new ResponseEntity<List<User>>(response,HttpStatus.OK);
//  }
//  
//  @PostMapping("api/employees/test")
//  public ResponseEntity<Page<User>> finPagination(@RequestBody UserDto userDto) {
//    Page<User> response = userService.findPageUser(userDto);
//    return new ResponseEntity<Page<User>>(response,HttpStatus.OK);
//  }
//}
//
