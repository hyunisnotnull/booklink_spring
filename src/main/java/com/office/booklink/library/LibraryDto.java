package com.office.booklink.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {
	private int 	L_NO;
	private int 	L_CODE;
    private String	L_NAME;
    private String 	L_ADDRESS;
    private String 	L_TEL;
    private double 	L_LATITUDE;
    private double 	L_LONGITUDE;
    private String 	L_HOMEPAGE;
    private String 	L_CLOSED;
    private String 	L_OPERATION_TIME;
    private String 	L_REG_DATE;
    private String 	L_MOD_DATE;
}
