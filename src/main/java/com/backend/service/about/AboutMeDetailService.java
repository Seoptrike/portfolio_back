package com.backend.service.about;

import com.backend.domain.about.AboutMeDetailSortDTO;
import com.backend.domain.about.AboutMeDetailVO;

public interface AboutMeDetailService {
  void createDetail(AboutMeDetailVO vo);

  void updateDetail(AboutMeDetailVO vo);

  void deleteDetail(int detailId);

  void updateSort(AboutMeDetailSortDTO dto);

  void deleteAllByAboutId(int aboutId);
}
