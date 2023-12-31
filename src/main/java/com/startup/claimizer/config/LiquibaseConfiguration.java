package com.startup.claimizer.config;

import com.startup.claimizer.common.DatasourceConstants;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration implements DatasourceConstants {
    @Autowired
    private Environment env;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(env.getProperty(DATASOURCE_CHANGE_LOG_MASTER_PROPERTY_KEY));
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty(DATASOURCE_DRIVER_CLASS_NAME_PROPERTY_KEY));
        dataSourceBuilder.url(env.getProperty(DATASOURCE_URL_PROPERTY_KEY));
        dataSourceBuilder.username(env.getProperty(DATASOURCE_USERNAME_PROPERTY_KEY));
        dataSourceBuilder.password(env.getProperty(DATASOURCE_PASSWORD_PROPERTY_KEY));
        return dataSourceBuilder.build();
    }
}
