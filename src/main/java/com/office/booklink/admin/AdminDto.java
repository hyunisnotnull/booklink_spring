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
	private String A_MAIL;
	private String A_PHONE;
	private String A_ROLE;
	private String A_REG_DATE;
	private String A_MOD_DATE;
}
