package org.jettyrest.api.sample;

import org.jettyrest.api.sample.entities.Employee;
import org.jettyrest.api.sample.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
@Transactional
public class EmployeeServiceImpl implements EmployeeService<Employee> {
    private Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository repository;

    @Autowired
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void update(String uuid, String attributeName, String attributeValue) {
        throw new NotImplementedException();
    }

    @Override
    public String save(Employee entity) {
        return repository.save(entity).getId();
    }

    @Override
    public Employee findByUUID(String uuid) {
        return repository.findOne(uuid);
    }

    @Override
    public void deleteByUUID(String uuid) {
        repository.delete(uuid);
        log.info("deleted {}", uuid);
    }
}
