package com.office.booklink.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int U_NO;
	private String U_ID;
	private String U_PW;
	private String U_PHONE;
	private String U_SEX;
	private String U_AGE;
	private String U_ZIPCODE;
	private String U_POST_ADDRESS;
	private String U_DETAIL_ADDRESS;
	private String U_SNS_ID;
	private int U_ACTIVE;
	private String U_LAST_LOGINED;
	private String U_REG_DATE;
	private String U_MOD_DATE;
}
