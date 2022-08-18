package com.malpan.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {
//     @Bean
//     public OpenAPI qiwenFileOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info().title("网盘项目 API")
//                         .description("基于springboot + vue 框架开发的Web文件系统，旨在为用户提供一个简单、方便的文件存储方案，能够以完善的目录结构体系，对文件进行管理 。")
//                         .version("v1.0.0")
//                         .license(new License().name("MIT").url("http://springdoc.org")))
//                 .externalDocs(new ExternalDocumentation()
//                         .description("网盘gitee地址")
//                         .url("https://www.gitee.com/qiwen-cloud/qiwen-file"));
//     }

    @Bean(value = "indexApi")
    public Docket indexApi() {
            return new Docket(DocumentationType.OAS_30)
                            .groupName("网站前端接口分组").apiInfo(apiInfo())
                            .select()
                            .apis(RequestHandlerSelectors.basePackage("com.malpan.file.controller"))
                            .paths(PathSelectors.any())
                            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                        .title("网盘项目 API")
                        .description("基于springboot + vue 框架开发的Web文件系统，旨在为用户提供一个简单、方便的文件存储方案，能够以完善的目录结构体系，对文件进行管理 。")
                        .version("1.0")
                        .build();
        }
}