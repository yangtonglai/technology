package com.shop.manage.system.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.shop.manage.system.contant.DataSourceConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 * @author Mr.joey
 */
@Configuration
public class DynamicDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.base")
    public DataSource baseDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.back-db")
    public DataSource backDBDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource baseDataSource, DataSource backDBDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>(16);
        targetDataSource.put(DataSourceConstants.BASE, baseDataSource);
        targetDataSource.put(DataSourceConstants.BACK_DB, backDBDataSource);
        return new DynamicDataSource(baseDataSource, targetDataSource);
    }
}
