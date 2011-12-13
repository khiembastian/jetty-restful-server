package org.jettyrest.api.employee.repositories;

import org.jettyrest.api.employee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
