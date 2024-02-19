package com.cms.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AttributeResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private long fieldValue;

    public AttributeResourceNotFoundException(String resourceName, long fieldValue){
        super(String.format("%s is not found: '%s'",resourceName,fieldValue)); // Post not found with id: 1

        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
