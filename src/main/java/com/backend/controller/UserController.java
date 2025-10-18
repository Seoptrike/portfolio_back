package com.backend.controller;

import com.backend.domain.user.UsersVO;
import com.backend.service.users.UserService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired UserService userService;

  @GetMapping("/portfolio/{username}")
  public HashMap<String, Object> getUserTotalData(@PathVariable String username) {
    return userService.getUserTotalData(username);
  }

  @GetMapping("/search/{username}")
  public List<UsersVO> searchUsername(@PathVariable String username) {
    return userService.searchUsername(username);
  }

  @GetMapping("/{username}")
  public UsersVO getUserData(@PathVariable String username) {
    return userService.getUserData(username);
  }

  @PutMapping("/{username}")
  public void softDeleteUser(@PathVariable String username) {
    userService.softDeleteUser(username);
  }

  @PutMapping
  public void updateUserData(@RequestBody UsersVO vo) {
    userService.updateUserData(vo);
  }
}
