package org.jettyrest.api.employee;

import org.jettyrest.api.employee.entities.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class EmployeeDTOTest {

    private EmployeeDTO dto;

    @Before
    public void setup() {
        dto = new EmployeeDTO("name", "ENGR");
    }


    @Test
    public void shouldCreateEntity() {
        assertNotNull(dto.getEntity());
    }


    @Test
    public void shouldProperlyCreateEntity() {
        Employee entity = dto.getEntity();
        assertEquals(dto.getDept(), entity.getDept());
        assertEquals(dto.getName(), entity.getName());
        assertNotNull(entity.getCreated());
    }
}