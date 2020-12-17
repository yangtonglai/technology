package com.shop.manage.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author admin
 */
@MapperScan("com.shop.manage.system.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShopManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopManageSystemApplication.class, args);
    }

}
