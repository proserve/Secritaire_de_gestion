package com.andima.secritaire.desktop.sample;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaConfiguration.class)
public class SampleAppFactory
{
    @Bean
    public Person person()
    {
        return new Person("Richard");
    }

    @Bean
    public SampleController sampleController()
    {
        return new SampleController();
    }
}