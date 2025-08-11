package com.backend.service.guestbook;

import com.backend.domain.guestbook.GuestbookRequestDTO;
import com.backend.domain.guestbook.GuestbookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuestBookService {

    List<GuestbookVO> getGuestbookByUsername(String username);

    void insertGuestBook(GuestbookRequestDTO dto);

    void updateGuestBook(GuestbookRequestDTO dto);

    void deleteGuestBook(int guestbookId);
}
