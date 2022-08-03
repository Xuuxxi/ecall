package com.hands.ecall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * http://localhost:8080/swagger-ui.html
 * @Author: Xuuxxi
 * @Date: 2022/7/22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true); //改为false则无法访问
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "https://www.4399.com", "联系人邮箱");
        return new ApiInfo(
                "ECALL", // 标题
                "ECALL接口文档", // 描述
                "v1.0", // 版本
                "https://www.4399.com", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
