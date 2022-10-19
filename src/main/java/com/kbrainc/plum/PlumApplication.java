package com.kbrainc.plum;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession; // REDIS_SESSION

import com.kbrainc.plum.rte.file.FileStorageProperties;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {"com.kbrainc.plum"})
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@ConfigurationPropertiesScan({"com.kbrainc.plum.config.security.properties.${database.dialect}"})
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200) // REDIS_SESSION
public class PlumApplication {
    public static String profile;
    
	@Value("${app.timezone}")
    private String serverTimezone;
	
	@Autowired
	com.fasterxml.jackson.databind.ObjectMapper objectMapper;
	
	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(serverTimezone));
		objectMapper.setTimeZone(TimeZone.getDefault());
	}
	
    public static void main(String[] args) {
        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        profile = applicationArguments.getOptionValues("spring.profiles.active").get(0).toString();
        SpringApplication.run(PlumApplication.class, args);
    }

}