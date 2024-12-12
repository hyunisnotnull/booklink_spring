package com.office.booklink.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${server.resource.event-img}")
	private String serverResourceEventImg;
	
	@Value("${server.cors.node}")
	private String serverCorsNode;
	
	@Value("${server.cors.react.user}")
	private String serverCorsReactUser;
	
	@Value("${server.cors.react.admin}")
	private String serverCorsReactAdmin;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("addResourceHandlers");
		
		registry.addResourceHandler("/event_images/**")
				.addResourceLocations(serverResourceEventImg);
		
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(serverCorsNode, serverCorsReactUser, serverCorsReactAdmin)
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
	
}
