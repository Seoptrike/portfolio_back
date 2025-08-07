package com.backend.mapper;

import com.backend.domain.AboutMeDetailSortDTO;
import com.backend.domain.AboutMeDetailVO;

import java.util.List;

public interface AboutMeDetailMapper {
    // 생성
    void insertDetail(AboutMeDetailVO vo);

    // 내용 수정
    void updateDetail(AboutMeDetailVO vo);

    // 삭제
    void deleteDetail(int detailId);

    // 정렬 순서 수정 (드래그앤드롭)
    void updateSort(AboutMeDetailSortDTO dto);

    // about_id로 전체 삭제 (자기소개서 삭제 시 같이)
    void deleteAllByAboutId(int aboutId);

    Integer findAboutIdByDetailId(int detailId);

    int countDetailsByAboutId(int aboutId);
}
