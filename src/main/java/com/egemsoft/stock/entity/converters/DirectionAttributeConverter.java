package com.egemsoft.stock.entity.converters;

import com.egemsoft.stock.entity.enums.Direction;

import javax.persistence.AttributeConverter;

public class DirectionAttributeConverter implements AttributeConverter<Direction, String> {

    @Override
    public String convertToDatabaseColumn(Direction direction) {
        if (direction == null)
            return null;

        switch (direction) {
            case REQUEST:
                return "REQUEST";
            case RESPONSE:
                return "RESPONSE";
            default:
                throw new IllegalArgumentException(direction + " Not supported");
        }

    }

    @Override
    public Direction convertToEntityAttribute(String s) {

        if (s == null)
            return null;

        switch (s) {
            case "REQUEST":
                return Direction.REQUEST;
            case "RESPONSE":
                return Direction.RESPONSE;
            default:
                throw new IllegalArgumentException(s + " not supported");
        }


    }
}
