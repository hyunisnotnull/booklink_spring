package com.office.booklink.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.booklink.util.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
		System.out.println(user);
		UserDto loginedUserDto = userService.isUser(user);
		
		if (loginedUserDto == null) return ResponseEntity.ok(new UserDto());
		System.out.println(loginedUserDto);
		return ResponseEntity.ok(loginedUserDto);
		
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserDto user) {
		log.info("[userController] addUser()");
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
		log.info("[userController] modifyUser()");
		log.info(user);
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
	@GetMapping("/testUser")
	public BodyBuilder testUser(@CookieValue("token") String value) {
		log.info("[userController] testUser()");
//		Cookie[] list = request.getCookies();
//		for(Cookie cookie:list) {
//			System.out.println(cookie.getName());
//				System.out.println(cookie.getValue());
//		}
		System.out.println("[userController] testUser()");
		System.out.println(value);
		
		try {
			Claims claims =  new JwtUtils().extractAllClaims(value);
			System.out.println(claims);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
	
		return ResponseEntity.ok();
		
	}

	
}
