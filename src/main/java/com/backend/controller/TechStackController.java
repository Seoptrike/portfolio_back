package com.backend.controller;

import com.backend.domain.TechStacksVO;
import com.backend.mapper.TechStackMapper;
import com.backend.service.stack.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://seoportfolio.vercel.app"
})
@RestController
@RequestMapping("/api/tech-stacks")
public class TechStackController {
    @Autowired
    TechStackService techStackService;

    @GetMapping
    public List<TechStacksVO> getStacksByCategory(@RequestParam(required = false) String category) {
        // category 파라미터가 null일 경우 전체 조회 등의 처리 가능
        return techStackService.selectByCategoryNullable(category);
    }
}
