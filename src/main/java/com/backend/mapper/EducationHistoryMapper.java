package com.backend.mapper;

import com.backend.domain.EducationHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EducationHistoryMapper {
    List<EducationHistoryVO> findEduHistoryByUserID(@Param("user_id") int userId);

    void insertEduHistory(EducationHistoryVO evo);

    void updateEduHistory(EducationHistoryVO evo);

    void deleteEduHistory(@Param("education_id") int education_id);
}
