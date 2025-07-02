package com.backend.controller;

import com.backend.domain.UsersVO;
import com.backend.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://seoportfolio.vercel.app"
})
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserAllList")
    public List<UsersVO> getUserAllList() {
        return userService.getUserAllList();
    }

}
