package com.office.booklink.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
	final private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/isUser")
	public ResponseEntity<Object> isUser(@RequestBody UserDto user) {
		log.info("[userController] isUser()");
		log.info("user: {}",user);
		UserDto loginedUserDto = userService.isUser(user);
		
		if (loginedUserDto == null) return ResponseEntity.ok(new UserDto());
		log.info("loginedUserDto: {}", loginedUserDto);
		return ResponseEntity.ok(loginedUserDto);
		
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserDto user) {
		log.info("[userController] addUser(): {}", user);
		if(userService.isUser(user) != null) {
			return ResponseEntity.ok(new UserDto());
	               	
		}
		userService.addUser(user);
		UserDto resultDto = new UserDto();
		resultDto.setU_ID(user.getU_ID());
		
		return ResponseEntity.ok(resultDto);
		
	}
	
	
	@PostMapping("/modifyUser")
	public ResponseEntity<Object> modifyUser(@RequestBody UserDto user) {
		log.info("[userController] modifyUser(): {}", user);
		userService.modifyUser(user);
		UserDto resultDto = new UserDto();
		resultDto.setU_ID(user.getU_ID());
		
		return ResponseEntity.ok(resultDto);
		
	}

	@PostMapping("/deleteUser")
	public ResponseEntity<Object> deleteUser(@RequestBody UserDto user) {
		log.info("[userController] deleteUser()");
		log.info(user);
		userService.deleteUser(user);
		UserDto resultDto = new UserDto();
		resultDto.setU_ID(user.getU_ID());
		
		return ResponseEntity.ok(resultDto);
		
	}
	
	@PostMapping("/getid")
	public ResponseEntity<Object> getid(@RequestBody UserDto user) {
		log.info("[userController] getid() {}", user);
		UserDto loginedUserDto = userService.getid(user);
		
		log.info(loginedUserDto);
		if (loginedUserDto == null) return ResponseEntity.ok(new UserDto());
		return ResponseEntity.ok(loginedUserDto);
		
	}

	@PostMapping("/getpw")
	public ResponseEntity<Object> getpw(@RequestBody UserDto user) {
		log.info("[userController] getpw() {}", user);
		UserDto loginedUserDto = userService.getpw(user);
		
		log.info(loginedUserDto);
		if (loginedUserDto == null) return ResponseEntity.ok(new UserDto());
		return ResponseEntity.ok(loginedUserDto);
		
	}
	
	@PostMapping("/updatepw")
	public ResponseEntity<Object> updatepw(@RequestBody UserDto user) {
		log.info("[userController] updatepw() {}", user);
		userService.upatepw(user);
		UserDto loginedUserDto = userService.isUser(user);
		
		log.info(loginedUserDto);
		if (loginedUserDto == null) return ResponseEntity.ok(new UserDto());
		return ResponseEntity.ok(loginedUserDto);
		
	}

	
}
