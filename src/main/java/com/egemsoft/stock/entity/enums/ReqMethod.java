package com.egemsoft.stock.dto;

public enum ReqMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");


    private String name;
    private ReqMethod(String s){
        name = s;
    }

    public boolean equalsName(String otherName){
        return name.equals(otherName);
    }
}
