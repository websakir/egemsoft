package com.egemsoft.stock.entity.enums;

public enum Direction {
    REQUEST("REQUEST"),
    RESPONSE("RESPONSE");

    private String name;
    private Direction(String s){
        name = s;
    }

    public boolean equalsName(String otherName){
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return "DIRECTION{" +
                "name='" + name + '\'' +
                '}';
    }
}
