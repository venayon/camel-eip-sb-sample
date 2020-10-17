package com.vrana.ps;


import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CamelRouteConfigTest extends CamelTestSupport {
    @Test
    public void test(){
        System.out.println(123);
    }
}
