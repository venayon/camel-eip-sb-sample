package com.vrana.ps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrana.ps.camel.api.xml.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.math.BigInteger;

public class Testme {
    private static Employee getEmploye() {
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

    public static void main(String[] args)  throws Exception{
        var context = JAXBContext.newInstance(Employee.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Employee employee = getEmploye();
        // Write to System.out
        m.marshal(employee, System.out);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, employee);
        // System.out.println(mapper.toString());;
        String asString = mapper.writeValueAsString(employee);

        System.out.println(asString);

    }
}
