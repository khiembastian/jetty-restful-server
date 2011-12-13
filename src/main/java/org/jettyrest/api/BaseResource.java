package org.jettyrest.api;

import org.jettyrest.api.exception.ApiException;
import org.jettyrest.api.exception.ErrorCode;
import org.jettyrest.api.validator.ApiValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class BaseResource {

    private ApiValidator validator;

    @Autowired
    public void setValidator(ApiValidator validator) {
        this.validator = validator;
    }


    public <T> void validate(T object) {

        Map<String, String> errorMaps = validator.validate(object);

        if (errorMaps != null && errorMaps.size() > 0) {
            throw new ApiException(buildErrorMessage(errorMaps), ErrorCode.INVALID_REQUEST_DATA);
        }
    }

    protected String buildErrorMessage(Map<String, String> errorMaps) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String property : errorMaps.keySet()) {
            stringBuffer.append(property).append("|").append(errorMaps.get(property)).append(",");
        }

        return stringBuffer.toString();
    }
}