package com.stackroute.config;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**This is to add the add configuration to view the h2 database
 * and by adding urlmapping it gives the url where we can view and acccess
 * the database
 */
@Configuration//Configuration
public class TrackConfig {

    @Bean   //This is to view the h2 database
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean servletRegistrationBean= new ServletRegistrationBean(new WebdavServlet());
        servletRegistrationBean.addUrlMappings("/console/*");
        return servletRegistrationBean;
    }
}
