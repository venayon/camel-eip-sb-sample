package com.vrana.ps.camel.api.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "department" , namespace="http://vrana.com/dept")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    Integer id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Department() {
        super();
    }

    public Department(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }
}
