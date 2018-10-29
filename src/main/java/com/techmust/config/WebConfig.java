package com.techmust.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; 

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer 
{	
	public WebConfig()
	{
		super();
	}

	@Override
    public void addInterceptors(final InterceptorRegistry registry)
	{
        registry.addInterceptor(new RequestInterceptor());
    }
}
