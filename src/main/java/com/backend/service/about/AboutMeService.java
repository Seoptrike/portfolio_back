package com.backend.service.about;

import com.backend.domain.AboutMeCreateRequestDTO;
import com.backend.domain.AboutMeVO;

import java.util.HashMap;
import java.util.List;

public interface AboutMeService {
    void createAboutMe(AboutMeVO vo);

    List<HashMap<String, Object>> getAboutMeListByUsername(String username);

    void updateAboutMe(AboutMeVO vo);

    void deleteAboutMe(int detailId);

    void createAboutMeWithDetails(AboutMeCreateRequestDTO dto);

}
