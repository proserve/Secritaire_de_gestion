package com.andima.secritaire.client.config;

import com.andima.secritaire.client.service.ProjectService;
import com.andima.secritaire.client.service.ProjectServiceImpl;
import com.andima.secritaire.config.jpa.JpaConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by GHIBOUB Khalid  on 12/08/2014.
 */
@Configuration
@Import(JpaConfiguration.class)
public class ServiceConfig {
    @Bean
    public ProjectService projectService(){
        return new ProjectServiceImpl();
    }
}
