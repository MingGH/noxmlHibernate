package com.inlineun.init;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

@Order(1)
public class Initializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebAppConfig.class);
		servletContext.addListener(new ContextLoaderListener(ctx));

		ctx.setServletContext(servletContext);
		
		//Log4jConfigListener  
       servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");  
       servletContext.addListener(Log4jConfigListener.class);
       
       //OpenSessionInViewFilter  
       OpenSessionInViewFilter hibernateSessionInViewFilter = new OpenSessionInViewFilter();  
       FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(  
               "hibernateFilter", hibernateSessionInViewFilter);  
       filterRegistration.addMappingForUrlPatterns(  
               EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/"); 
       

     //DemoServlet 
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(2);
	}

}