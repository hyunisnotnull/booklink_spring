package com.office.booklink.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/admin")
public class AdminController {
	final private AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping("/isAdmin")
	public ResponseEntity<Object> isAdmin(@RequestBody AdminDto admin) {
		log.info("[adminController] isAdmin() {}", admin);
		AdminDto loginedAdminDto = adminService.isAdmin(admin);
		
		log.info(loginedAdminDto);
		if (loginedAdminDto == null) return ResponseEntity.ok(new AdminDto());
		return ResponseEntity.ok(loginedAdminDto);
		
	}
	
	@PostMapping("/addAdmin")
	public ResponseEntity<Object> addAdmin(@RequestBody AdminDto admin) {
		log.info("[adminController] addAdmin(): {}", admin);
		if(adminService.isAdmin(admin) != null) {
			return ResponseEntity.ok(new AdminDto());
	               	
		}
		adminService.addAdmin(admin);
		AdminDto resultDto = new AdminDto();
		resultDto.setA_ID(admin.getA_ID());
		
		return ResponseEntity.ok(resultDto);
		
	}
	
	
	@PostMapping("/modifyAdmin")
	public ResponseEntity<Object> modifyAdmin(@RequestBody AdminDto admin) {
		log.info("[adminController] modifyAdmin(): {}", admin);
		adminService.modifyAdmin(admin);
		AdminDto resultDto = new AdminDto();
		resultDto.setA_ID(admin.getA_ID());
		
		return ResponseEntity.ok(resultDto);
		
	}

	@PostMapping("/deleteAdmin")
	public ResponseEntity<Object> deleteAdmin(@RequestBody AdminDto admin) {
		log.info("[adminController] deleteAdmin()");
		log.info(admin);
		adminService.deleteAdmin(admin);
		AdminDto resultDto = new AdminDto();
		resultDto.setA_ID(admin.getA_ID());
		
		return ResponseEntity.ok(resultDto);
		
	}
	
	@PostMapping("/getid")
	public ResponseEntity<Object> getid(@RequestBody AdminDto admin) {
		log.info("[adminController] getid() {}", admin);
		AdminDto loginedAdminDto = adminService.getid(admin);
		
		log.info(loginedAdminDto);
		if (loginedAdminDto == null) return ResponseEntity.ok(new AdminDto());
		return ResponseEntity.ok(loginedAdminDto);
		
	}

	@PostMapping("/getpw")
	public ResponseEntity<Object> getpw(@RequestBody AdminDto admin) {
		log.info("[adminController] getpw() {}", admin);
		AdminDto loginedAdminDto = adminService.getpw(admin);
		
		log.info(loginedAdminDto);
		if (loginedAdminDto == null) return ResponseEntity.ok(new AdminDto());
		return ResponseEntity.ok(loginedAdminDto);
		
	}
	
	@PostMapping("/updatepw")
	public ResponseEntity<Object> updatepw(@RequestBody AdminDto admin) {
		log.info("[adminController] updatepw() {}", admin);
		adminService.upatepw(admin);
		AdminDto loginedAdminDto = adminService.isAdmin(admin);
		
		log.info(loginedAdminDto);
		if (loginedAdminDto == null) return ResponseEntity.ok(new AdminDto());
		return ResponseEntity.ok(loginedAdminDto);
		
	}
	
	
}
