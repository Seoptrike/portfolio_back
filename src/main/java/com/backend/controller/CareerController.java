package com.backend.controller;

import com.backend.domain.EducationHistoryVO;
import com.backend.domain.WorkExperiencesVO;
import com.backend.service.career.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://seoportfolio.vercel.app"
})
@RestController
@RequestMapping("/api/career")
public class CareerController {

    @Autowired
    private CareerService careerService;

    // 📌 WorkExperience 등록
    @PostMapping("/work")
    public void insertWorkExp(@RequestBody WorkExperiencesVO wvo) {
        careerService.insertWorkExp(wvo);
    }

    // 📌 WorkExperience 수정
    @PutMapping("/work")
    public void updateWorkExp(@RequestBody WorkExperiencesVO wvo) {
        careerService.updateWorkExp(wvo);
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
