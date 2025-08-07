package com.backend.service.about;

import com.backend.domain.AboutMeCreateRequestDTO;
import com.backend.domain.AboutMeDetailRequestDTO;
import com.backend.domain.AboutMeDetailVO;
import com.backend.domain.AboutMeVO;
import com.backend.mapper.AboutMeDetailMapper;
import com.backend.mapper.AboutMeMapper;
import com.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class AboutMeServiceImpl implements AboutMeService {
    @Autowired
    AboutMeMapper aboutMeMapper;

    @Autowired
    AboutMeDetailMapper detailMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void createAboutMe(AboutMeVO vo) {
        aboutMeMapper.insertAboutMe(vo);
    }

    @Override
    public List<HashMap<String, Object>> getAboutMeListByUsername(String username) {
        int userId = userMapper.findUserID(username);
        List<HashMap<String, Object>> list = aboutMeMapper.getAboutMeListByUserId(userId);
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public void updateAboutMe(AboutMeVO vo) {
        aboutMeMapper.updateAboutMe(vo);
    }

    @Transactional
    @Override
    public void deleteAboutMe(int detailId) {
        Integer aboutId = detailMapper.findAboutIdByDetailId(detailId);
        if (aboutId == null) {
            throw new RuntimeException("유효하지 않은 detail_id 입니다." + detailId);
        }
        detailMapper.deleteDetail(detailId);
        int count = detailMapper.countDetailsByAboutId(aboutId);
        if (count == 0) {
            aboutMeMapper.deleteAboutMe(detailId);
        }
    }

    @Override
    public void createAboutMeWithDetails(AboutMeCreateRequestDTO dto) {
        int userId = userMapper.findUserID(dto.getUsername());
        AboutMeVO aboutMeVO = new AboutMeVO();
        aboutMeVO.setUserId(userId);
        aboutMeMapper.insertAboutMe(aboutMeVO);
        int generatedAboutId = aboutMeVO.getAboutId();
        AboutMeDetailVO detailVO = new AboutMeDetailVO();
        detailVO.setAboutId(generatedAboutId); // ⬅️ 위에서 받은 PK 사용
        detailVO.setTitle(dto.getTitle());     // ⬅️ DTO에서 title 가져오기
        detailVO.setContent(dto.getContent()); // ⬅️ DTO에서 content 가져오기
        detailMapper.insertDetail(detailVO);
    }
}
