package com.backend.mapper;

import com.backend.domain.guestbook.GuestbookResponseDTO;
import com.backend.domain.guestbook.GuestbookVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GuestBookMapper {
  List<GuestbookResponseDTO> getGuestBookById(@Param("hostId") int hostId);

  void insertGuestBook(GuestbookVO vo);

  void updateGuestBook(GuestbookVO vo);

  void deleteGuestBook(@Param("guestbookId") int guestbookId);

  GuestbookVO findGuestBookById(@Param("guestbookId") int guestbookId);
}
