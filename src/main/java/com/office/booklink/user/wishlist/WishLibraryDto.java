package com.office.booklink.user.wishlist;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishLibraryDto {

	private int ML_NO;
	
	@JsonProperty("ML_U_ID")
	private String ML_U_ID;
	
	@JsonProperty("ML_L_CODE")
	private int ML_L_CODE;
	
	private String ML_REG_DATE;
	private String ML_MOD_DATE;
	
}
