package com.office.booklink.user.wishlist;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	private final WishlistService wishlistService;
	
	public WishlistController(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}
	
	// 찜하기 기능
    @PostMapping("/addWishBook")
    public ResponseEntity<Object> wishBook(@RequestBody WishBookDto wishBookDto) {
    	log.info("[WishListController] wishBook() : {}", wishBookDto);

        try {
            // UserService에서 찜하기 처리
        	wishlistService.addWishBook(wishBookDto);
            return ResponseEntity.ok("찜하기 성공");
        } catch (Exception e) {
            log.error("[userController] 찜하기 실패", e);
            return ResponseEntity.status(500).body("찜하기 실패");
        }
    }
    
    // 찜목록 가져오기
    @PostMapping("/wishBooks")
    public ResponseEntity<Object> getUserWishBooks(@RequestBody Map<String, String> requestBody) {
    	String userId = requestBody.get("userId");
    	
        log.info("[WishlistController] getUserWishBooks() : {}", userId);

        try {
            // 사용자 찜 목록 가져오기
            List<WishBookDto> wishBooks = wishlistService.getUserWishBooks(userId);
            return ResponseEntity.ok(wishBooks);
        } catch (Exception e) {
            log.error("[WishlistController] 찜 목록 조회 실패", e);
            return ResponseEntity.status(500).body("찜 목록 조회 실패");
        }
    }
    
    // 찜 취소
    @DeleteMapping("/cancleWishBook")
    public ResponseEntity<Object> removeWishBook(@RequestBody WishBookDto wishBookDto) {
        log.info("[WishlistController] removeWishBook() : {}", wishBookDto);

        try {
            // 찜한 도서 취소 처리
            wishlistService.cancleWishBook(wishBookDto);
            return ResponseEntity.ok("찜 취소 성공");
        } catch (Exception e) {
            log.error("[WishlistController] 찜 취소 실패", e);
            return ResponseEntity.status(500).body("찜 취소 실패");
        }
    }
    

}
