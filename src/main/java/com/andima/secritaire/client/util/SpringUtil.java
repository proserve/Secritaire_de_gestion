package com.andima.secritaire.client.util;

import com.andima.secritaire.client.config.ServiceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by GHIBOUB Khalid  on 13/08/2014.
 */
public class SpringUtil {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class);
    public static <T> T getBean(Class<T> aClass){
        return context.getBean(aClass);
    }
}
