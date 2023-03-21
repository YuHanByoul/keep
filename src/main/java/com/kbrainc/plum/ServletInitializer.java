package com.kbrainc.plum;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        InitialContext initialContext = null;
        String profile = null;
        try {
            initialContext = new javax.naming.InitialContext();
            profile = (String) initialContext.lookup("java:comp/env/spring.profiles.active");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        ConfigurationFactory.profile = profile;
        return application.sources(PlumApplication.class);
    }

}
