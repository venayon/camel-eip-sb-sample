package com.vrana.ps.camel.route;


import com.vrana.ps.camel.api.Response;
import com.vrana.ps.camel.api.Sample;
import com.vrana.ps.camel.api.w.Users;
import com.vrana.ps.camel.processor.SampleProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
class CamelApplication extends RouteBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CamelApplication.class);

    @Value("${server.port}")
    String serverPort;

    @Autowired
    private Users users;

    @BeanInject(value ="sampleProcessor" )
    private SampleProcessor sampleProcessor;


    @Override
    public void configure() throws Exception {
        LOGGER.info(" Rest Started " + serverPort);

        restConfiguration()
                .port(serverPort)
                .component("servlet")
                .bindingMode(RestBindingMode.auto);


        rest("/api")
                .get("/").route().transform(simple("Shiva help me")).endRest()

                .post("/add").type(Sample.class)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .to("log:mylogger?showAll=true")
                .process(sampleProcessor)
                .endRest()


                .post("/addUser").consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Sample.class).outType(Response.class)
                .route()
                .to("log:mylogger?showAll=true")
                .process(sampleProcessor)
                .endRest();
    }

}