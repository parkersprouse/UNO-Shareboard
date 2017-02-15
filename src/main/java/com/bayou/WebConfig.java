package com.bayou;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Joshua Eaton on 2/8/17.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedMethods("PUT", "DELETE", "GET", "POST")
        .allowCredentials(true).maxAge(3600);
    }
    //add the below back once a postman whitelist solution is found
    //.allowedOrigins("http://localhost:3000","https://uno-shareboard-webapp-dev.herokuapp.com/","https://uno-shareboard-webapp-staging.herokuapp.com/","https://uno-shareboard-webapp-prod.herokuapp.com/")

}
