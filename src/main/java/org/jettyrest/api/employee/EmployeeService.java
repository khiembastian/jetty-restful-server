package org.jettyrest.api.employee;

public interface EmployeeService<T> {
    void update(String uuid, String attributeName, String attributeValue);

    String save(T entity);

    T findByUUID(String uuid);

    void deleteByUUID(String uuid);
}
