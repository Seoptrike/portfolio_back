package com.backend.mapper;

import com.backend.domain.career.WorkExperiencesVO;
import com.backend.domain.work.WorkDetailsVO;
import java.util.List;

public interface WorkDetailMapper {
  /**
   * 특정 사용자의 모든 경력(work_experiences)과 각 경력에 속한 상세 업무(work_details)를 함께 조회합니다.
   *
   * @param userId 사용자 ID
   * @return 중첩된 구조의 경력 목록
   */
  List<WorkExperiencesVO> findWorkDetailsByUserId(int userId);

  /**
   * 특정 경력(work_experience)에 새로운 상세 업무(work_detail)를 추가합니다.
   *
   * @param workDetailsVO 추가할 상세 업무 정보 (workId, title, content)
   */
  void insertWorkDetail(WorkDetailsVO workDetailsVO);

  /**
   * 기존 상세 업무(work_detail)의 내용을 수정합니다.
   *
   * @param workDetailsVO 수정할 상세 업무 정보 (detailId, title, content)
   */
  void updateWorkDetail(WorkDetailsVO workDetailsVO);

  /**
   * 특정 상세 업무(work_detail)를 삭제합니다.
   *
   * @param detailId 삭제할 상세 업무의 ID
   */
  void deleteWorkDetail(int detailId);
}
