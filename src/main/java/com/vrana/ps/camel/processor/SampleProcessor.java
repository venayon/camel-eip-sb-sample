package com.vrana.ps.camel.processor;


import com.vrana.ps.EmpJsn;
import com.vrana.ps.camel.api.xml.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component(value = "sampleProcessor")
public class SampleProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        EmpJsn ej = (EmpJsn) exchange.getIn().getBody(EmpJsn.class);
        LOGGER.info(" {} ",ej);
        Employee emp = getEmploye();
        exchange.getMessage().setBody(emp);

    }

    private Employee getEmploye() {
        Employee emp = new Employee();
        Employee.Address address  = new Employee.Address();
        address.setCity("Glasgow");
        address.setMobile("10000100");
        address.setStreet("Bathwel street");
        address.setZipcode(BigInteger.TWO);
        emp.setAddress(address);
        emp.setEmpId(100L);
        emp.setLastName("LastName");
        emp.setSalary(BigInteger.TEN);
        emp.setTitle("Mr");
        return emp;
    }
}
