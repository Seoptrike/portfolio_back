package com.backend.mapper;

import com.backend.domain.WorkExperiencesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface WorkExperienceMapper {
    List<WorkExperiencesVO> findWorkExpByUserId(@Param("user_id") int userId);

    void insertWorkExp(WorkExperiencesVO wvo);

    void updateWorkExp(WorkExperiencesVO wvo);

    void deleteWorkExp(@Param("work_id") int workId);
}
