package com.vrana.ps;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
class CamelApplication extends RouteBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CamelApplication.class);

    @Value("${server.port}")
    String serverPort;


    @Override
    public void configure() throws Exception {
        LOGGER.info(" Rest Started "+serverPort);

        restConfiguration()
                .component("servlet")
                .port(serverPort)
                .bindingMode(RestBindingMode.json);

        rest().get("/api")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(constant("Welcome to world!!!"));

    }
}