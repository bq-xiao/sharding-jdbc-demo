package com.sharding.jdbc.sharding.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(exposeProxy = true)
public class ShardingJDBCDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCDataApplication.class, args);
    }

}
