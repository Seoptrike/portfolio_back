package com.backend.controller;

import com.backend.dao.UsersDAO;
import com.backend.domain.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    UsersDAO udao;

    @GetMapping("/getUserListAll")
    public List<UsersVO> getUserListAll() {
        return udao.getUserListAll();
    }
}
