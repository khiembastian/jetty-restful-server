package org.jettyrest.api.sample.dao;

public interface DAO<T> {
    String save(T employee);

    T findByUUID(String uuid);

    void deleteByUUID(String uuid);

    void update(String uuid, String attributeName, String attributeValue);
}
