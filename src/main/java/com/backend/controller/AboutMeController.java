package com.backend.controller;

import com.backend.domain.AboutMeCreateRequestDTO;
import com.backend.domain.AboutMeDetailVO;
import com.backend.service.about.AboutMeDetailService;
import com.backend.service.about.AboutMeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/about")
public class AboutMeController {
    @Autowired
    AboutMeService aboutMeService;

    @Autowired
    AboutMeDetailService detailService;

    @PostMapping
    public void createAboutMe(@RequestBody AboutMeCreateRequestDTO dto) {
        aboutMeService.createAboutMeWithDetails(dto);
    }

    // ✅ 자기소개서 조회
    @GetMapping("/{username}")
    public List<HashMap<String, Object>> getAboutMeListByUsername(@PathVariable String username) {
        return aboutMeService.getAboutMeListByUsername(username);
    }

    // ✅ 상세 항목 추가
    @PostMapping("/details")
    public void addDetail(@RequestBody AboutMeDetailVO vo) {
        log.info("### Received DTO for insert: {}", vo);
        detailService.createDetail(vo);
    }
    // 상세 항목 수정
    @PutMapping("/details")
    public void updateDetail(@RequestBody AboutMeDetailVO vo){
        detailService.updateDetail(vo);
    }
    // 상세 항목 삭제
    @DeleteMapping("/details/{detailId}")
    public void deleteDetail(@PathVariable int detailId){
        aboutMeService.deleteAboutMe(detailId);
    }

    //상세항목 순서변경 추구 구현

}
