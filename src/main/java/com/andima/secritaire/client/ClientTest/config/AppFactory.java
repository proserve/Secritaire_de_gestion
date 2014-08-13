package com.andima.secritaire.client.ClientTest.config;

import com.andima.secritaire.client.ClientTest.Person;
import com.andima.secritaire.client.ClientTest.controller.EnterDetailsController;
import com.andima.secritaire.client.ClientTest.controller.ProjectInterfaceController;
import com.andima.secritaire.client.ClientTest.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Import(value = {ServiceConfig.class})
public class AppFactory
{
    @Bean
    public Person person()
    {
        return new Person();
    }

    @Bean
    public WelcomeController welcomePageController() throws IOException
    {
        return (WelcomeController) loadController("/Welcome.fxml");
    }

    @Bean
    public EnterDetailsController enterDetailsController() throws IOException
    {
        return (EnterDetailsController) loadController("/EnterDetails.fxml");
    }

    @Bean
    public ProjectInterfaceController projectInterfaceController() throws IOException
    {
        return (ProjectInterfaceController) loadController("/ProjectInterface.fxml");
    }

    protected Object loadController(String url) throws IOException
    {
        InputStream fxmlStream = null;
        try
        {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        }
        finally
        {
            if (fxmlStream != null)
            {
                fxmlStream.close();
            }
        }
    }
}
