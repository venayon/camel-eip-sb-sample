package com.vrana.ps.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrana.ps.camel.api.Response;
import com.vrana.ps.camel.api.Sample;
import com.vrana.ps.camel.api.json.EmployeeJson;
import com.vrana.ps.camel.api.xml.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.TypeConverter;
import org.springframework.stereotype.Component;

@Component(value = "sampleProcessor")
public class SampleProcessor implements Processor {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void process(Exchange exchange) throws Exception {
        EmployeeJson emp = exchange.getIn().getBody(EmployeeJson.class);
        Response<String> res = new Response();
        res.setMessage(mapper.writeValueAsString(emp));
        exchange.getMessage().setBody(res);

    }
}
