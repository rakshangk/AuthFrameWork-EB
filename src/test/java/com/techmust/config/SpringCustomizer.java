package com.techmust.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>  {

	  @Override
	  public void customize(ConfigurableServletWebServerFactory server) {
	    server.setContextPath("/techmust");
	  }
}
