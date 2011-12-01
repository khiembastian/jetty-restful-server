package org.jettyrest.api.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeResource {
    private Logger log = LoggerFactory.getLogger(EmployeeResource.class);
    private Service<Employee> employeeService;


    public String save(Employee employee) {
        String uuid = employeeService.save(employee);
        return uuid;
    }

    public Employee findByUUID(String uuid) {
        Employee employee = employeeService.findByUUID(uuid);
        return employee;
    }

    public void deleteByUUID(String uuid) {
        employeeService.deleteByUUID(uuid);
    }

    public void update(String uuid, String attributeName, String attributeValue) {
        employeeService.update(uuid, attributeName, attributeValue);
    }

    public void setEmployeeService(Service<Employee> employeeService) {
        this.employeeService = employeeService;
    }
}
