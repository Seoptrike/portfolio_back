package com.backend.service.guestbook;

import com.backend.domain.guestbook.GuestbookRequestDTO;
import com.backend.domain.guestbook.GuestbookResponseDTO;
import java.util.List;

public interface GuestBookService {

  List<GuestbookResponseDTO> getGuestbookByUsername(String username);

  void insertGuestBook(GuestbookRequestDTO dto);

  void updateGuestBook(GuestbookRequestDTO dto);

  void deleteGuestBook(int guestbookId);
}
