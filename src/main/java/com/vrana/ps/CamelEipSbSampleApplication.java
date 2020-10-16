package com.vrana.ps;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.awt.*;

@SpringBootApplication
public class CamelEipSbSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamelEipSbSampleApplication.class, args);
    }
}

