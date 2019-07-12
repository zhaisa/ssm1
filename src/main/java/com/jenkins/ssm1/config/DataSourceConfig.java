package com.jenkins.ssm1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import javax.sql.DataSource;

import static com.jenkins.ssm1.config.BaseConfig.*;
/**
 * 配置主数据源
 *
 * @author caiyuyu
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = DATA_SOURCE_NAME)
    @ConfigurationProperties(prefix = DATA_SOURCE_PROPERTIES)
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
