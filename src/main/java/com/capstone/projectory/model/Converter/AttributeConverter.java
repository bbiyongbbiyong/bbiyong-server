package com.capstone.projectory.model.Converter;

public interface AttributeConverter<X, Y> {
    Y convertToDatabaseColumn(X var1);
    X convertToEntityAttribute(Y var1);
}