package com.office.booklink.user.wishlist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.office.booklink.library.LibraryDto;

@Mapper
public interface WishlistMapper {

	void insertWishBook(WishBookDto wishBookDto);

	List<WishBookDto> selectUserWishBooks(@Param("userId") String userId);

	void deleteWishBook(WishBookDto wishBookDto);

	void updateWishBook(WishBookDto wishBookDto);

	void insertMyLibrary(WishLibraryDto wishLibraryDto);

	void deleteMyLibrary(WishLibraryDto wishLibraryDto);

	List<LibraryDto> selectUserWishLibraries(String userId);

}
