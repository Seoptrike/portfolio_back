package com.backend.controller;

import com.backend.domain.stack.UserStackRequestDTO;
import com.backend.domain.stack.UserStackResponseDTO;
import com.backend.domain.stack.UserStacksVO;
import com.backend.service.stack.UserStackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-stack")
public class UserStackController {

  @Autowired UserStackService userStackService;

  @GetMapping("/{userId}")
  List<UserStacksVO> findStackByUserID(@PathVariable int userId) {
    return userStackService.findStackByUserID(userId);
  }

  @PostMapping
  void insertUserStack(@RequestBody UserStackRequestDTO dto) {
    userStackService.insertUserStack(dto);
  }

  @PutMapping
  void updateUserStack(@RequestBody UserStackRequestDTO dto) {
    userStackService.updateUserStack(dto);
  }

  @GetMapping("/{username}")
  List<UserStackResponseDTO> selectUserStackByUserId(@PathVariable String username) {
    return userStackService.selectUserStackByUserId(username);
  }
}
