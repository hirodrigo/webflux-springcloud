package com.rodrigo.userservice.adapter.web.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldErrorUtil {

    public static String getJsonFieldName(FieldError fieldError) {
        if (fieldError.contains(ConstraintViolationImpl.class)) {
            Field[] fields = fieldError.unwrap(ConstraintViolationImpl.class)
                    .getRootBeanClass().getDeclaredFields();

            for (Field field : fields) {
                if (fieldError.getField().equals(field.getName()) && field.isAnnotationPresent(JsonProperty.class)) {
                    return field.getAnnotation(JsonProperty.class).value();
                }
            }
        }

        return fieldError.getField();
    }


}
