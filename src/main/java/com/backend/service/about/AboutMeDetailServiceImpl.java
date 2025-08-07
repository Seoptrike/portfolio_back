package com.backend.service.about;

import com.backend.domain.AboutMeDetailSortDTO;
import com.backend.domain.AboutMeDetailVO;
import com.backend.mapper.AboutMeDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AboutMeDetailServiceImpl implements AboutMeDetailService {
    @Autowired
    AboutMeDetailMapper detailMapper;

    @Transactional
    @Override
    public void createDetail(AboutMeDetailVO vo) {
        detailMapper.insertDetail(vo);
    }

    @Transactional
    @Override
    public void updateDetail(AboutMeDetailVO vo) {
        detailMapper.updateDetail(vo);
    }

    @Transactional
    @Override
    public void deleteDetail(int detailId) {
        detailMapper.deleteDetail(detailId);
    }

    @Transactional
    @Override
    public void updateSort(AboutMeDetailSortDTO dto) {
        detailMapper.updateSort(dto);
    }

    @Transactional
    @Override
    public void deleteAllByAboutId(int aboutId) {
        detailMapper.deleteAllByAboutId(aboutId);
    }
}
