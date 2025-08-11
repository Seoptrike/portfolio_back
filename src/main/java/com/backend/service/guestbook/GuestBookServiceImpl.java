package com.backend.service.guestbook;

import com.backend.domain.guestbook.GuestbookRequestDTO;
import com.backend.domain.guestbook.GuestbookVO;
import com.backend.mapper.GuestBookMapper;
import com.backend.mapper.UserMapper;
import com.backend.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {
    private final UserMapper userMapper;
    private final GuestBookMapper guestBookMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<GuestbookVO> getGuestbookByUsername(String username) {
        int hostId = userMapper.findUserID(username);
        return guestBookMapper.getGuestBookById(hostId);
    }

    @Override
    public void insertGuestBook(GuestbookRequestDTO dto) {
        int hostId = userMapper.findUserID(dto.getUsername());
        long guestId = SecurityUtil.getCurrentUserId();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@게스트ID:"+guestId);
        if (hostId==guestId) {
            throw new IllegalArgumentException("자신의 방명록에는 글을 남길 수 없습니다.");
        }
        GuestbookVO vo = modelMapper.map(dto, GuestbookVO.class);
        vo.setHostId(hostId);
        vo.setGuestId((int) guestId);
        guestBookMapper.insertGuestBook(vo);
    }

    @Override
    public void updateGuestBook(GuestbookRequestDTO dto) {
        long currentUserId = SecurityUtil.getCurrentUserId();
        int hostId = userMapper.findUserID(dto.getUsername());
        GuestbookVO originVo = guestBookMapper.findGuestBookById(dto.getGuestbookId());
        if (originVo.getGuestId() != currentUserId) {
            throw new AccessDeniedException("수정할 권한이 없습니다.");
        }
        GuestbookVO voToUpdate = new GuestbookVO();
        voToUpdate.setGuestId((int) currentUserId);
        voToUpdate.setHostId(hostId);
        voToUpdate.setGuestbookId(dto.getGuestbookId());
        voToUpdate.setMessage(dto.getMessage());
        guestBookMapper.updateGuestBook(voToUpdate);
    }

    @Override
    public void deleteGuestBook(int guestbookId) {
        // 1. 현재 로그인한 사용자의 ID를 가져옵니다.
        long currentUserId = SecurityUtil.getCurrentUserId();

        // 2. 삭제하려는 글 '하나만'을 guestbookId로 정확히 조회합니다.
        GuestbookVO guestbook = guestBookMapper.findGuestBookById(guestbookId);
        if(guestbook!=null){
            boolean hasAuthority = guestbook.getGuestId() == currentUserId || guestbook.getHostId() == currentUserId;
            if (!hasAuthority) {
                throw new AccessDeniedException("삭제할 권한이 없습니다.");
            }
            // 4. 권한이 확인되었으므로, 삭제를 진행합니다.
            guestBookMapper.deleteGuestBook(guestbookId);
        }
    }

}
