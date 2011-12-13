package org.jettyrest.api.employee;

import org.jettyrest.api.employee.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Service
@Path("/employee")
@Produces("application/json")
public class EmployeeResource {

    private Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    private EmployeeService<Employee> employeeService;

    @POST
    public Response save(Employee employee) {
        String uuid = employeeService.save(employee);
        URI uri = UriBuilder.fromPath("/").build();
        return Response.created(uri).entity(new CreatedId(uuid)).build();
    }

    @GET
    @Path("{uuid}")
    public Employee findByUUID(@PathParam("uuid") String uuid) {
        Employee employee = employeeService.findByUUID(uuid);
        return employee;
    }

    @DELETE
    public void deleteByUUID(String uuid) {
        employeeService.deleteByUUID(uuid);
    }

    public void update(String uuid, String attributeName, String attributeValue) {
        employeeService.update(uuid, attributeName, attributeValue);
    }

    public void setEmployeeService(EmployeeService<Employee> employeeService) {
        this.employeeService = employeeService;
    }
}
