package com.vrana.ps.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrana.ps.camel.api.json.EmployeeJson;
import com.vrana.ps.camel.api.xml.Employee;
import com.vrana.ps.camel.api.Response;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "xmlProcessor")
public class XmlProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlProcessor.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void process(Exchange exchange) throws Exception {
        EmployeeJson emplyee = exchange.getIn().getBody(EmployeeJson.class);
        Response<String> res = new Response<>(mapper.writeValueAsString(emplyee));

        LOGGER.info("Test - {} ",res);
        exchange.getMessage().setBody(res);
    }
}
