package com.kbrainc.plum.rte.configuration;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * application 프로퍼티 객체를 관리한다.
 *
 * <pre>
 * com.kbrainc.plum.rte.configuration
 * - ConfigurationFactory.java
 * </pre> 
 *
 * @ClassName : ConfigurationFactory
 * @Description : application 프로퍼티 객체를 관리한다
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class ConfigurationFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactory.class);
    private static ConfigurationFactory instance = null;
    private PropertiesConfiguration applicationConfig = null;

    /**.
     * @Title : getInstance
     * @Description : 환경설정 Factory 인스턴스를 반환한다
     * @return ConfigurationFactory
     */
    public static ConfigurationFactory getInstance() {
        if (instance == null) {
            synchronized (ConfigurationFactory.class) {
                instance = new ConfigurationFactory();
                PropertiesConfiguration p1 = null;
                try {
                    p1 = new PropertiesConfiguration("application.properties");
                    p1.setReloadingStrategy(new FileChangedReloadingStrategy());
                    instance.setApplicationConfig(p1);
                } catch (ConfigurationException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return instance;
    }

    /**.
     * @Title : getApplicationConfig
     * @Description : application.properties의 참조를 리턴한다
     * @return Configuration Configuration객체
     */
    public Configuration getApplicationConfig() {
        return applicationConfig;
    }

    /**.
     * @Title : setApplicationConfig
     * @Description : application.properties의 참조를 셋팅한다
     * @param applicationConfig PropertiesConfiguration객체
     * @return void
     */
    private void setApplicationConfig(PropertiesConfiguration applicationConfig) {
        this.applicationConfig = applicationConfig;
    }
}