package com.backend.service.guestbook;

import com.backend.domain.guestbook.GuestbookRequestDTO;
import com.backend.domain.guestbook.GuestbookVO;
import com.backend.domain.guestbook.GuestbookResponseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuestBookService {

    List<GuestbookResponseDTO> getGuestbookByUsername(String username);

    void insertGuestBook(GuestbookRequestDTO dto);

    void updateGuestBook(GuestbookRequestDTO dto);

    void deleteGuestBook(int guestbookId);
}
