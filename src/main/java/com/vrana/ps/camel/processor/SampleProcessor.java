package com.vrana.ps.camel.processor;

import com.vrana.ps.camel.api.Response;
import com.vrana.ps.camel.api.Sample;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.TypeConverter;
import org.springframework.stereotype.Component;

@Component(value = "sampleProcessor")
public class SampleProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Sample sample = exchange.getIn().getBody(Sample.class);
        Response res = new Response();
        res.setMessage(sample.toString());
        exchange.getOut().setBody(res);

    }
}
