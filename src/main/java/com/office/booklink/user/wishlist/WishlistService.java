package com.office.booklink.user.wishlist;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
