package com.capstone.projectory.user.converter;

import javax.persistence.Converter;

@Converter
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String yn) {
        return "Y".equalsIgnoreCase(yn);
    }
}
