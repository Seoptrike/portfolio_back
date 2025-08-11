package com.backend.mapper;

import com.backend.domain.career.EducationHistoryVO;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface EducationHistoryMapper {
  List<EducationHistoryVO> findEduHistoryByUserID(@Param("userId") int userId);

  void insertEduHistory(EducationHistoryVO evo);

  void updateEduHistory(EducationHistoryVO evo);

  void deleteEduHistory(@Param("educationId") int educationId);
}
