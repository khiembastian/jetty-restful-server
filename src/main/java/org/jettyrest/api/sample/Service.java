package org.jettyrest.api.sample;

public interface Service<T> {
    Void update(String uuid, String attributeName, String attributeValue);

    String save(T entity);

    T findByUUID(String uuid);

    void deleteByUUID(String uuid);
}
