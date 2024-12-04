package com.office.booklink.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

	AdminDto isAdmin(AdminDto admin);

	AdminDto addAdmin(AdminDto admin);

	AdminDto modifyAdmin(AdminDto admin);

	AdminDto deleteAdmin(AdminDto admin);

}