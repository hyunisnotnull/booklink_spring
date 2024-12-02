package com.office.booklink.user.wishlist;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishBookDto {
	
	private int W_NO;
	
	@JsonProperty("W_U_ID")
    private String W_U_ID;

    @JsonProperty("W_ISBN13")
    private String W_ISBN13;

    @JsonProperty("W_AUTHORS")
    private String W_AUTHORS;

    @JsonProperty("W_NAME")
    private String W_NAME;

    @JsonProperty("W_PUBLISHER")
    private String W_PUBLISHER;

    @JsonProperty("W_BOOKIMAGEURL")
    private String W_BOOKIMAGEURL;
	    
	private String W_REG_DATE;
	private String W_MOD_DATE;

}
