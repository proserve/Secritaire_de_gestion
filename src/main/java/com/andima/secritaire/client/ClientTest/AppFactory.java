package com.andima.secritaire.client.ClientTest;

import com.andima.secritaire.client.ClientTest.controller.EnterDetailsController;
import com.andima.secritaire.client.ClientTest.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
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
