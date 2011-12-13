package org.jettyrest.api.validator;

import java.util.Map;

public interface ApiValidator<T> {
    Map<String, String> validate(T object);
}
