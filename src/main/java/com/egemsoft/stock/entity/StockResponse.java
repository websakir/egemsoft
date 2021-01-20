package com.egemsoft.stock.entity;

import lombok.Data;

import java.util.List;

@Data
public class StockResponse {

    private int code;
    private Stock data;
}
