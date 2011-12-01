package org.jettyrest.api.sample;

import java.util.Map;

public interface ApiValidator<T> {
    Map<String,String> validate(T object);
}
