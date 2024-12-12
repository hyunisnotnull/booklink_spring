package com.office.booklink.library.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.booklink.library.LibraryDto;
import com.office.booklink.library.LibraryService;

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
    		try {
    			apiService.addCount(userIp);
			} catch (Exception e) {
	    		response.sendError(404,"비정상적인 호출입니다.");
				return null;
			}
    	} else {
    		apiService.addNew(userIp);
    	}
    	
    	try {
    	List<LibraryDto> libraries = libraryService.searchRegion(region);
            return libraries;
		} catch (Exception e) {
			response.sendError(500,"데이터베이스 접속 에러");
			return null;
		}

    }
    
    @GetMapping("/name/{name}")
    public List<LibraryDto> searchName(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    	log.info("searchName()");
    	String userIp =  request.getRemoteAddr();
    	int accCount =  apiService.getCount(userIp);
    	if (accCount >= 10) {
    		response.sendError(500,"사용 횟수 초과");
    		return null;
    	} else if (accCount > 0) {
    		try {
    			apiService.addCount(userIp);
			} catch (Exception e) {
	    		response.sendError(500,"비정상적인 호출입니다.");
	    		return null;
			}
    	} else {
    		apiService.addNew(userIp);
    	}
    	
    	try {
    		List<LibraryDto> libraries = libraryService.searchName(name);
    		return libraries;
		} catch (Exception e) {
			response.sendError(500,"데이터베이스 접속 에러");
			return null;
		}
    }
        
}
