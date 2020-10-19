package com.vrana.ps;

public class EmpJsn {
    private String lastname;
    private String empId;

    public EmpJsn() {
        super();
    }

    public EmpJsn(String lastname, String empId) {
        this.lastname = lastname;
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmpJsn{");
        sb.append("lastname='").append(lastname).append('\'');
        sb.append(", empId='").append(empId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
