package com.vrana.ps;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = { CamelAutoConfiguration.class})
@ContextConfiguration
public class TestMe {
    //https://www.baeldung.com/apache-camel-spring-boot
    @Autowired
    protected CamelContext camelContext;
    @EndpointInject("mock:foo")
    protected MockEndpoint foo;

    @Test
    @DirtiesContext
    public void testMocksAreValid() throws Exception {
        foo.message(0)
                .header("bar")
                .isEqualTo("ABC");
        System.out.printf(camelContext.toString());
        MockEndpoint.assertIsSatisfied(camelContext);
    }

}
