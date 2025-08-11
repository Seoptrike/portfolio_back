package com.backend.mapper;

import com.backend.domain.career.WorkExperiencesVO;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface WorkExperienceMapper {
  List<WorkExperiencesVO> findWorkExpByUserId(@Param("userId") int userId);

  void insertWorkExp(WorkExperiencesVO wvo);

  void updateWorkExp(WorkExperiencesVO wvo);

  void deleteWorkExp(@Param("workId") int workId);
}
