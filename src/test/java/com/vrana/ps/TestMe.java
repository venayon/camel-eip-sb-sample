package com.vrana.ps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {CamelAutoConfiguration.class})
@ContextConfiguration
public class TestMe {

    @Autowired
    protected CamelContext camelContext;
    @EndpointInject("mock:foo")
    protected MockEndpoint foo;

    @Test
    @DirtiesContext
    public void testMocksAreValid() throws Exception {
       /* foo.message(0)
                .header("bar")
                .isEqualTo("ABC");
        System.out.printf(camelContext.toString());
        MockEndpoint.assertIsSatisfied(camelContext);*/
    }



}

