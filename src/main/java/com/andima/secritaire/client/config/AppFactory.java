package com.andima.secritaire.client.config;

import com.andima.secritaire.client.controller.ProjectInterfaceController;
import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Import(value = {ServiceConfig.class})
public class AppFactory {
    @Bean
    public ProjectInterfaceController projectInterfaceController() throws IOException {
        return (ProjectInterfaceController) loadController("/fxml/ProjectInterface.fxml");
    }

    protected Object loadController(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }
}
