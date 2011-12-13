package org.jettyrest.api.sample.repositories;

import org.jettyrest.api.sample.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
