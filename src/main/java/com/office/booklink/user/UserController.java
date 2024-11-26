package com.office.booklink.user;

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
//		userService.isUser(user);

//		UserDto loginedUsrDto = userService.isUser(u_id, u_pw);
//		if (loginedUserDto != null)
//			session.setAttribute("loginedUser", loginedUserDto);
//		else
//			nextPage = "member/login_ng";
		
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping("/testUser")
	public BodyBuilder testUser(@CookieValue("token") String value) {
		log.info("[userController] testUser()");
//		Cookie[] list = request.getCookies();
//		for(Cookie cookie:list) {
//			System.out.println(cookie.getName());
//				System.out.println(cookie.getValue());
//		}
		
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
