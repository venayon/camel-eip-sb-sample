package com.vrana.ps;

import com.vrana.ps.camel.api.xml.Employee;
import org.apache.camel.CamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT  )
@ContextConfiguration
public class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CamelContext camelContext;

    @Test
    public void testServiceRestCall_Get() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testServiceRestCall_POST() {
        ResponseEntity<Employee> response = restTemplate.getForEntity("/api/transform", Employee.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testServiceRestCall_transformj() {
        String jsnStr = "{\"empId\":100,\"lastName\":\"LastName\",\"title\":\"Mr\",\"salary\":10,\"address\":{\"city\":\"Glasgow\",\"street\":\"Bathwel street\",\"zipcode\":2,\"mobile\":\"10000100\"}}";
        HttpHeaders headers = new HttpHeaders();
        Employee emp = new Employee();
        HttpEntity<String> request = new HttpEntity<String>(null, headers);
        ResponseEntity<Employee> response = restTemplate.postForEntity("/api/transformj", request, Employee.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }



}




