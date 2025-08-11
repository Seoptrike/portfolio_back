package com.backend.mapper;

import com.backend.domain.guestbook.GuestbookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface GuestBookMapper {
    List<GuestbookVO> getGuestBookById(@Param("hostId") int hostId);

    void insertGuestBook(GuestbookVO vo);

    void updateGuestBook(GuestbookVO vo);

    void deleteGuestBook(@Param("guestbookId") int guestbookId);

    GuestbookVO findGuestBookById(@Param("guestbookId") int guestbookId);
}
