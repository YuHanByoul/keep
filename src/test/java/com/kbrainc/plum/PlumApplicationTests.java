package com.kbrainc.plum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class PlumApplicationTests {

    static {
        ConfigurationFactory.profile = "local";
    }
    
	@Test
	public void contextLoads() {
//		contextLoads
	}
}
