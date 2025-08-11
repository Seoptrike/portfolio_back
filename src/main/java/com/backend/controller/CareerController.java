package com.backend.controller;

import com.backend.domain.career.EducationHistoryVO;
import com.backend.domain.career.WorkExpRequestDTO;
import com.backend.domain.career.WorkExperiencesVO;
import com.backend.service.career.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
public class CareerController {

  @Autowired private CareerService careerService;

  // 📌 WorkExperience 등록
  @PostMapping("/work")
  public void insertWorkExp(@RequestBody WorkExpRequestDTO dto) {
    careerService.insertWorkExp(dto);
  }

  // 📌 WorkExperience 수정
  @PutMapping("/work")
  public void updateWorkExp(@RequestBody WorkExpRequestDTO dto) {
    careerService.updateWorkExp(dto);
  }

  // 📌 WorkExperience 삭제
  @DeleteMapping("/work/{workId}")
  public void deleteWorkExp(@PathVariable int workId) {
    careerService.deleteWorkExp(workId);
  }

  // 📌 EducationHistory 등록
  @PostMapping("/education")
  public void insertEduHistory(@RequestBody EducationHistoryVO evo) {
    careerService.insertEduHistory(evo);
  }

  // 📌 EducationHistory 수정
  @PutMapping("/education")
  public void updateEduHistory(@RequestBody EducationHistoryVO evo) {
    careerService.updateEduHistory(evo);
  }

  // 📌 EducationHistory 삭제
  @DeleteMapping("/education/{eduId}")
  public void deleteEduHistory(@PathVariable int eduId) {
    careerService.deleteEduHistory(eduId);
  }
}
