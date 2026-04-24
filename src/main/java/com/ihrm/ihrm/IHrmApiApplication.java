package com.ihrm.ihrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ihrm.ihrm.mapper")
public class IHrmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IHrmApiApplication.class, args);
    }

}
