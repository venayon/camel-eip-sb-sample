package com.vrana.ps.camel.route;


import com.vrana.ps.camel.api.*;
import com.vrana.ps.camel.api.json.EmployeeJson;
import com.vrana.ps.camel.api.xml.Employee;
import com.vrana.ps.camel.processor.SampleProcessor;
import com.vrana.ps.camel.processor.XmlProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.apache.camel.impl.DefaultCamelContext;
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
        CamelContext context = new DefaultCamelContext();
        context.setTypeConverterStatisticsEnabled(true);

   restConfiguration()
                .port(serverPort)
                .component("servlet")
                .bindingMode(RestBindingMode.json_xml);

        onException(JsonValidationException.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(501));

        rest("/api")
                .post("/userJson").type(Sample.class)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .type(EmployeeJson.class).outType(Response.class)
                .route()
                .to("log:mylogger?showAll=true")
                .process(xmlProcessor)
                .endRest()

                .post("/userXml")
                .to("log:mylogger?showAll=true")
                //.consumes(MediaType.APPLICATION_XML_VALUE)
                //.produces(MediaType.APPLICATION_JSON_VALUE).outType(Response.class)
                .route()
                .tracing().log(LoggingLevel.ERROR, "userXml ------- coming.............")
                .routeId("XML-VALIDATION")
                .to("validator:employe.xsd")
                .process(xmlProcessor)
                .endRest();

                 from("direct:validateJson")
                .routeId("direct-route-json-validator")
                .tracing().log("Came to route")
                .to("json-validator:classpath:stock.json")
                .process(sampleProcessor)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

    }

}