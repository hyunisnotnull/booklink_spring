package com.office.booklink.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	private int A_NO;
	private String A_ID;
	private String A_PW;
	private String A_PHONE;
	private String A_SEX;
	private String A_AGE;
	private String A_ZIPCODE;
	private String A_POST_ADDRESS;
	private String A_DETAIL_ADDRESS;
	private String A_SNS_ID;
	private int A_ACTIVE;
	private String A_LAST_LOGINED;
	private String A_REG_DATE;
	private String A_MOD_DATE;
}
