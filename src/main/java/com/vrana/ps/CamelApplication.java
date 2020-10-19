package com.vrana.ps;


import com.vrana.ps.camel.api.xml.Employee;
import com.vrana.ps.camel.processor.SampleProcessor;
import com.vrana.ps.camel.processor.XmlProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
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
    private String serverPort;

    @BeanInject(value ="sampleProcessor" )
    private SampleProcessor sampleProcessor;

    @BeanInject(value ="xmlProcessor" )
    private XmlProcessor xmlProcessor;



    @Override
    public void configure() throws Exception {
        LOGGER.info(" Rest Started " + serverPort);

        restConfiguration()
                .port(serverPort)
                .component("servlet")
                .bindingMode(RestBindingMode.json_xml);

        rest("/api")
                .get("/").route()
                .routeId("API-TEST")
                .to("log:mylogger?showAll=true")
                .transform(simple("Shiva help me"))
                .endRest()

          .get("/transform")
                .produces(MediaType.APPLICATION_XML_VALUE)
                .outType(Employee.class)
                .route()
                .to("log:mylogger?showAll=true")
                .process(xmlProcessor)
                .endRest()

           .post("/transformj")
                .consumes(MediaType.APPLICATION_JSON_VALUE).type(EmpJsn.class)
                .produces(MediaType.APPLICATION_XML_VALUE).outType(Employee.class)
                .route()
                .to("log:mylogger?showAll=true")
                .process(sampleProcessor)
                .endRest();


    }

}