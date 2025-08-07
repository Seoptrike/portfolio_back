package com.backend.controller;

import com.backend.domain.UserStackRequestDTO;
import com.backend.domain.UserStacksVO;
import com.backend.mapper.UserStackMapper;
import com.backend.service.stack.UserStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user-stack")
public class UserStackController {

    @Autowired
    UserStackService userStackService;

    @GetMapping("/{userId}")
    List<UserStacksVO> findStackByUserID(@PathVariable int userId){
        return userStackService.findStackByUserID(userId);
    }

    @PostMapping
    void insertUserStack(@RequestBody UserStackRequestDTO dto){
        userStackService.insertUserStack(dto);
    }

    @PutMapping
    void updateUserStack(@RequestBody UserStackRequestDTO dto){
        userStackService.updateUserStack(dto);
    }

    @GetMapping("/{username}")
    List<HashMap<String,Object>> selectUserStackByUserId(@PathVariable String username){
        return userStackService.selectUserStackByUserId(username);
    }

}
