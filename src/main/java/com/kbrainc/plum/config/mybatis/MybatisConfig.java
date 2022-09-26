package com.kbrainc.plum.config.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.egovframe.rte.psl.dataaccess.mapper.MapperConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

@Configuration
@EnableTransactionManagement
public class MybatisConfig { 
	
    private static org.apache.commons.configuration.Configuration applicationConfig = ConfigurationFactory.getInstance().getApplicationConfig();

    private static String projectPackage = applicationConfig.getString("project.package");
    
    private static String databaseDialect = applicationConfig.getString("database.dialect");

    @Bean(name = "sqlSession")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) 
            throws Exception { 
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); 
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setTypeAliasesPackage(projectPackage);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mybatis/" + databaseDialect + "/mapper/**/*.xml")); 
        return sqlSessionFactoryBean.getObject(); 
    }
    
    @Bean
    public MapperScannerConfigurer initMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperConfigurer();
        msc.setBasePackage(projectPackage);
        return msc;
    }
}