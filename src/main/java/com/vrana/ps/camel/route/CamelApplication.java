package com.vrana.ps.camel.route;


import com.vrana.ps.camel.api.OrderJsn;
import com.vrana.ps.camel.api.Response;
import com.vrana.ps.camel.api.Sample;
import com.vrana.ps.camel.processor.SampleProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
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

    @Override
    public void configure() throws Exception {
        LOGGER.info(" Rest Started " + serverPort);
        CamelContext context = new DefaultCamelContext();
        context.setTypeConverterStatisticsEnabled(true);

        restConfiguration()
                .port(serverPort)
                .component("servlet")
                .bindingMode(RestBindingMode.auto);


        onException(JsonValidationException.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(501));

        rest("/api")
                .get("/").route().transform(simple("Shiva help me")).endRest()

                .post("/add").type(Sample.class)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .to("log:mylogger?showAll=true")
                .process(sampleProcessor)
                .endRest()


                .post("/addUser")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Sample.class).outType(Response.class)
                .route()
                .to("log:mylogger?showAll=true")
                .process(sampleProcessor)
                .endRest()

                .post("/order")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(OrderJsn.class)
                .outType(Response.class)
                .id("stock-order")
                .route()
                .to("log:mylogger?showAll=true")
                .to("direct:validateJson");

                 from("direct:validateJson")
                .routeId("direct-route-json-validator")
                .tracing().log("Came to route")
                .to("json-validator:classpath:stock.json")
                         .process(sampleProcessor)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));




    }

}