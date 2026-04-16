package com.ihrm.ihrm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("iHRM人力资源后台管理系统API文档")
                        .description("内部OA管理系统接口文档")
                        .version("1.0.0"));
    }
}