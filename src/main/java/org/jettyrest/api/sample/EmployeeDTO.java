package org.jettyrest.api.sample;

import org.jettyrest.api.sample.entities.Employee;

public class EmployeeDTO {

    private String name;

    private String dept;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    protected Employee getEntity() {
        return new Employee(name, dept);
    }
}
