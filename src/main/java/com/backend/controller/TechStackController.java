package com.backend.controller;

import com.backend.domain.stack.TechStacksVO;
import com.backend.service.stack.TechStackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tech-stacks")
public class TechStackController {
  @Autowired TechStackService techStackService;

  @GetMapping
  public List<TechStacksVO> getStacksByCategory(@RequestParam(required = false) String category) {
    // category 파라미터가 null일 경우 전체 조회 등의 처리 가능
    return techStackService.selectByCategoryNullable(category);
  }
}
