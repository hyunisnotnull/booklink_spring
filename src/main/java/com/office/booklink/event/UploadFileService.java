package com.office.booklink.event;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UploadFileService {

//	@Value("${server.name}")
//	private String serverName;
	
	@Value("${server.static.event-img}")
	private String serverStaticProfileImg;
	
	@Value("${server.dir-separator}")
	private String serverDirSeparator;
	
	public String upload(String e_title, MultipartFile file) {
		log.info("upload()");
		
		boolean result = false;
		
		String fileOriName = file.getOriginalFilename();
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		
		String uploadDir = serverStaticProfileImg + e_title;
		
		UUID uuid = UUID.randomUUID();
		String uniqueFileName = uuid.toString().replaceAll("-", "");
		
		File saveFile = new File(uploadDir.concat(serverDirSeparator).concat(uniqueFileName).concat(fileExtension));
		
		if (!saveFile.exists())
			saveFile.mkdirs();
		
		try {
			file.transferTo(saveFile);
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result) {
			log.info("FILE UPLOAD SUCCESS!!");
			return uniqueFileName + fileExtension;
			
		} else {
			log.info("FILE UPLOAD FAIL!!");
			return null;
			
		}
		
	}
	
}
