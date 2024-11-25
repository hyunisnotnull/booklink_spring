package com.office.booklink.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

	private int e_no;
    private String e_title;
    private String e_image;
    private String e_url;
    private String e_desc;
    private int e_active;
    private String e_start_date;
    private String e_end_date;
    private String e_reg_date;
    private String e_mod_date;
    
}
