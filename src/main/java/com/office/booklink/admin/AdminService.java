package com.office.booklink.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminService {

	@Autowired
    private AdminMapper adminMapper;

	public AdminService (AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
		
	}
	
	public AdminDto isAdmin(AdminDto admin) {
		log.info("[adminService] isAdmin()");
	
		return adminMapper.isAdmin(admin);
	}

	public AdminDto addAdmin(AdminDto admin) {
		log.info("[adminService] addAdmin()");
		return adminMapper.addAdmin(admin);
	}

	public AdminDto modifyAdmin(AdminDto admin) {
		log.info("[adminService] modifyAdmin()");
		log.info(admin);
		return adminMapper.modifyAdmin(admin);
		
	}

	public AdminDto deleteAdmin(AdminDto admin) {
		log.info("[adminService] deletefyAdmin()");
		log.info(admin);
		return adminMapper.deleteAdmin(admin);
		
	}

	public AdminDto getid(AdminDto admin) {
		log.info("[adminService] getid() {}", admin);
		return adminMapper.getid(admin);
	}

}
