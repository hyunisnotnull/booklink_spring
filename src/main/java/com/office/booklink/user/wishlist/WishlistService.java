package com.office.booklink.user.wishlist;

import java.util.List;

import org.springframework.stereotype.Service;

import com.office.booklink.library.LibraryDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class WishlistService {
	
	private final WishlistMapper wishlistMapper;
	
	public WishlistService(WishlistMapper wishlistMapper) {
		this.wishlistMapper = wishlistMapper;
	}
	
	public void addWishBook(WishBookDto wishBookDto) {
		log.info("addWishBook() : {}", wishBookDto);
		wishlistMapper.insertWishBook(wishBookDto);
		
	}

	public List<WishBookDto> getUserWishBooks(String userId) {
		log.info("getUserWishBooks() : {}", userId);
		return wishlistMapper.selectUserWishBooks(userId);
	}

	public void cancleWishBook(WishBookDto wishBookDto) {
		log.info("cancleWishBook()");
		wishlistMapper.deleteWishBook(wishBookDto);
		
	}

	public void readWishBook(WishBookDto wishBookDto) {
		log.info("readWishBook()");
	    wishlistMapper.updateWishBook(wishBookDto);
		
	}

	public void addMyLibrary(WishLibraryDto wishLibraryDto) throws Exception {
		log.info("addMyLibrary() : {}", wishLibraryDto);
		
		List<LibraryDto> userWishLibraries = wishlistMapper.selectUserWishLibraries(wishLibraryDto.getML_U_ID());
		
		if (userWishLibraries.size() >= 5) {
            throw new Exception("찜할 수 있는 도서관은 최대 5개까지입니다.");
        }
		
		wishlistMapper.insertMyLibrary(wishLibraryDto);
		
	}

	public void cancleMyLibrary(WishLibraryDto wishLibraryDto) {
		log.info("cancleWishBook()");
		wishlistMapper.deleteMyLibrary(wishLibraryDto);
		
	}

	public List<LibraryDto> getUserWishLibraries(String userId) {
		log.info("getUserWishLibraries() : {}", userId);
	    return wishlistMapper.selectUserWishLibraries(userId);
	}

}
