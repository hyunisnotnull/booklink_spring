package com.office.booklink.library;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_LIBRARY")
public class LibraryDto {
	
	@Id
    private int L_CODE;
	
    private String L_NAME;
    private String L_ADDRESS;
    private String L_TEL;
    private double L_LATITUDE;
    private double L_LONGITUDE;
    private String L_HOMEPAGE;
    private String L_CLOSED;
    private String L_OPERATION_TIME;
    private String L_REG_DATE;
    private String L_MOD_DATE;

}
