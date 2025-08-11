package com.backend.service.work;

import com.backend.domain.career.WorkExperiencesVO;
import com.backend.domain.work.WorkDetailsVO;

import java.util.List;

public interface WorkService {

    /** 사용자 전체 경력 + 상세업무 중첩 조회 */
    List<WorkExperiencesVO> getWorkDetailsByUsername(String username);

    /** 상세업무 추가 */
    void addWorkDetail(WorkDetailsVO workDetailsVO);

    /** 상세업무 수정 */
    void updateWorkDetail(WorkDetailsVO workDetailsVO);

    /** 상세업무 삭제 */
    void removeWorkDetail(int detailId);
}
