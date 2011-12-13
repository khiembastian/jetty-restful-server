package org.jettyrest.api.employee;

import org.jettyrest.api.employee.entities.Employee;
import org.jettyrest.api.exception.ApiException;
import org.jettyrest.api.exception.ErrorCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeResourceTest {

    private EmployeeResource employeeResource;
    private Employee employee;

    @Mock
    private EmployeeService<Employee> service;

    @Before
    public void setup() {
        employeeResource = new EmployeeResource();
        employeeResource.setEmployeeService(service);
        employee = new Employee();
    }


    @Test
    public void shouldHaveSaveMethod() {
        given(service.save(employee)).willReturn("a uuid");
        String uuid = employeeResource.save(employee);
        assertNotNull(uuid);
        assertThat(uuid, is(equalTo("a uuid")));
    }


    @Test
    public void shouldCallDAOWhenSaving() {
        employeeResource.save(employee);
        verify(service).save(employee);
    }


    @Test
    public void shouldHaveFindByUUID() {
        given(service.findByUUID("my uuid")).willReturn(employee);
        Employee foundEmployee = employeeResource.findByUUID("my uuid");
        assertNotNull(foundEmployee);
        assertThat(foundEmployee, is(equalTo(employee)));
    }


    @Test
    public void shouldCallFindByUUID() {
        employeeResource.findByUUID("my c uuid");
        verify(service).findByUUID("my c uuid");
    }


    @Test
    public void shouldCallDelete() {
        employeeResource.deleteByUUID("my uuid to delete");
        verify(service).deleteByUUID("my uuid to delete");
    }


    @Test
    public void shouldCallUpdate() {
        String uuid = "uuid";
        String attributeName = "name";
        String attributeValue = "Mango";
        employeeResource.update(uuid, attributeName, attributeValue);
        verify(service).update(uuid, attributeName, attributeValue);
    }


    @Test(expected = ApiException.class)
    public void shouldThrowExceptionForNotSupportAttributeName() {
        String uuid = "uuid";
        String attributeName = "yargablabla";
        String attributeValue = "Mango";

        doThrow(new ApiException("not supported attribute", ErrorCode.INVALID_REQUEST_DATA)).when(service).update(uuid, attributeName, attributeValue);
        employeeResource.update(uuid, attributeName, attributeValue);

    }
}