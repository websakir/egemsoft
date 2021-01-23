package com.egemsoft.stock.dto;

import javax.persistence.AttributeConverter;

public class ReqMethodAttributeConverter implements AttributeConverter<ReqMethod, String> {

    @Override
    public String convertToDatabaseColumn(ReqMethod reqMethod) {

        if(reqMethod == null)
            return null;

        switch (reqMethod){
            case GET:
                return "GET";
            case PUT:
                return "PUT";
            case POST:
                return "POST";
            case PATCH:
                return "PATCH";
            case DELETE:
                return "DELETE";
                default:
                    throw  new IllegalArgumentException(reqMethod + " Not supported");
        }

    }

    @Override
    public ReqMethod convertToEntityAttribute(String s) {

        if (s == null)
            return  null;


        switch (s){
            case "GET":
                return ReqMethod.GET;
            case "PUT":
                return ReqMethod.PUT;
            case "POST":
                return ReqMethod.POST;
            case "PATCH":
                return ReqMethod.PATCH;
            case "DELETE":
                return ReqMethod.DELETE;
                default:
                    throw new IllegalArgumentException(s + " Not supported");
        }

    }
}
