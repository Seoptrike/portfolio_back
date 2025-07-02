package com.backend.controller;

import com.backend.domain.UsersVO;
import com.backend.service.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://seoportfolio.vercel.app"
})
@RestController
@RequestMapping("/api/total")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @PostMapping("/getUserTotalData")
    public HashMap<String,Object> getUserTotalData(@RequestBody UsersVO vo){
        return portfolioService.getUserTotalData(vo.getUsername());
    }
}
