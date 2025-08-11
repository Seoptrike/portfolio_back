package com.backend.controller;

import com.backend.domain.guestbook.GuestbookRequestDTO;
import com.backend.domain.guestbook.GuestbookVO;
import com.backend.service.guestbook.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestBookController {
    private final GuestBookService guestBookService;

    // 1. 특정 사용자의 방명록 목록 조회
    @GetMapping("/{username}")
    public ResponseEntity<List<GuestbookVO>> getGuestbookList(@PathVariable String username) {
        List<GuestbookVO> guestbookList = guestBookService.getGuestbookByUsername(username);
        return ResponseEntity.ok(guestbookList);
    }

    // 2. 방명록 작성
    @PostMapping
    public ResponseEntity<Void> createGuestbook(@RequestBody GuestbookRequestDTO dto) {
        guestBookService.insertGuestBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created 응답
    }

    // 3. 방명록 수정
    @PutMapping
    public ResponseEntity<Void> updateGuestbook(@RequestBody GuestbookRequestDTO dto) {
        guestBookService.updateGuestBook(dto);
        return ResponseEntity.ok().build(); // 200 OK 응답
    }

    // 4. 방명록 삭제
    @DeleteMapping("/{guestbookId}")
    public ResponseEntity<Void> deleteGuestbook(@PathVariable int guestbookId) {
        guestBookService.deleteGuestBook(guestbookId);
        return ResponseEntity.ok().build(); // 200 OK 응답
    }
}
