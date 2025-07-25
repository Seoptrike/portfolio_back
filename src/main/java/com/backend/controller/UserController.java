package com.backend.controller;

import com.backend.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://seoportfolio.vercel.app"
})
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/portfolio/{username}")
    public HashMap<String, Object> getUserTotalData(@PathVariable String username) {
        return userService.getUserTotalData(username);
    }
}
