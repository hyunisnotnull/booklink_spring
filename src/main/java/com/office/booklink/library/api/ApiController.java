package com.office.booklink.library.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.office.booklink.library.LibraryDto;
import com.office.booklink.library.LibraryEntity;
import com.office.booklink.library.LibraryService;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/library")
public class ApiController {
	
	@Autowired
    private LibraryService libraryService;
	private ApiService apiService;
	
	public ApiController (LibraryService libraryService, ApiService apiService ) {
		this.libraryService = libraryService;
		this.apiService = apiService;
	}
	
    
	// OPEN API
    @GetMapping("/region/{region}")
    public List<LibraryDto> searchRegion(@PathVariable("region") String region, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	log.info("searchRegion()");
    	String userIp =  request.getRemoteAddr();
    	int accCount =  apiService.getCount(userIp);
    	if (accCount >= 10) {
    		response.sendError(404,"사용 횟수 초과");
    		return null;
    	} else if (accCount > 0) {
    		apiService.addCount(userIp);
    	} else {
    		apiService.addNew(userIp);
    	}
    	
    	List<LibraryDto> libraries = libraryService.searchRegion(region);
            return libraries;
    }
    
    @GetMapping("/name/{name}")
    public List<LibraryDto> searchName(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	log.info("searchName()");
    	String userIp =  request.getRemoteAddr();
    	int accCount =  apiService.getCount(userIp);
    	if (accCount >= 10) {
    		response.sendError(404,"사용 횟수 초과");
    		return null;
    	} else if (accCount > 0) {
    		apiService.addCount(userIp);
    	} else {
    		apiService.addNew(userIp);
    	}
    	
    	List<LibraryDto> libraries = libraryService.searchName(name);
    	System.out.println(libraries);
            return libraries;
    }
    
    
}
