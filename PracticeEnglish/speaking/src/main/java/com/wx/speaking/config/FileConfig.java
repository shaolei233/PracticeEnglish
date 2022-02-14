package com.wx.speaking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:/file/image/");
        registry.addResourceHandler("/record/**").addResourceLocations("file:/file/record/");
        registry.addResourceHandler("/tmp/**").addResourceLocations("file:/file/tmp/");
        registry.addResourceHandler("/file/tmp/**").addResourceLocations("file:/file/tmp/");
    }
}
