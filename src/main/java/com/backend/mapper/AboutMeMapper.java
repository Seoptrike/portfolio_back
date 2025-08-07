package com.backend.mapper;

import com.backend.domain.AboutMeVO;

import java.util.HashMap;
import java.util.List;

public interface AboutMeMapper {
    // 생성
    void insertAboutMe(AboutMeVO vo);

    // 단건 조회
    AboutMeVO getAboutMeById(int aboutId);

    // 사용자 전체 목록 조회
    List<HashMap<String,Object>> getAboutMeListByUserId(int userId);

    // 수정 (updated_at만 갱신)
    void updateAboutMe(AboutMeVO vo);

    // 삭제
    void deleteAboutMe(int aboutId);
}
