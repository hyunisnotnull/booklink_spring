package com.office.booklink.library;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_LIBRARY")
public class LibraryEntity {
	
	@Id
    @JsonProperty("libCode")
    private String L_CODE;

    @JsonProperty("libName")
    private String L_NAME;

    @JsonProperty("address")
    private String L_ADDRESS;

    @JsonProperty("tel")
    private String L_TEL;

    @JsonProperty("latitude")
    private double L_LATITUDE;

    @JsonProperty("longitude")
    private double L_LONGITUDE;

    @Lob
    @JsonProperty("homepage")
    private String L_HOMEPAGE;

    @Lob
    @JsonProperty("closed")
    private String L_CLOSED;

    @Lob
    @JsonProperty("operatingTime")
    private String L_OPERATION_TIME;
    

}
