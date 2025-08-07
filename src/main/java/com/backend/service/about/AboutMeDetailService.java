package com.backend.service.about;

import com.backend.domain.AboutMeDetailSortDTO;
import com.backend.domain.AboutMeDetailVO;


public interface AboutMeDetailService {
    void createDetail(AboutMeDetailVO vo);

    void updateDetail(AboutMeDetailVO vo);

    void deleteDetail(int detailId);

    void updateSort(AboutMeDetailSortDTO dto);

    void deleteAllByAboutId(int aboutId);
}
