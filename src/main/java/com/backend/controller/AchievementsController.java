package com.backend.controller;

import com.backend.domain.career.WorkExperiencesVO;
import com.backend.domain.work.WorkDetailsVO;
import com.backend.service.work.WorkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/achieve")
@RequiredArgsConstructor
public class AchievementsController {

  private final WorkService workService;

  /** 특정 사용자의 경력 + 상세업무 전체 조회 */
  @GetMapping("/{username}")
  public ResponseEntity<List<WorkExperiencesVO>> getWorkDetailsByUserId(
      @PathVariable String username) {
    return ResponseEntity.ok(workService.getWorkDetailsByUsername(username));
  }

  /** 상세업무 추가 */
  @PostMapping
  public ResponseEntity<Void> addWorkDetail(@RequestBody WorkDetailsVO workDetailsVO) {
    workService.addWorkDetail(workDetailsVO);
    return ResponseEntity.ok().build();
  }

  /** 상세업무 수정 */
  @PutMapping
  public ResponseEntity<Void> updateWorkDetail(@RequestBody WorkDetailsVO workDetailsVO) {
    workService.updateWorkDetail(workDetailsVO);
    return ResponseEntity.ok().build();
  }

  /** 상세업무 삭제 */
  @DeleteMapping("/{detailId}")
  public ResponseEntity<Void> deleteWorkDetail(@PathVariable int detailId) {
    workService.removeWorkDetail(detailId);
    return ResponseEntity.noContent().build();
  }
}
