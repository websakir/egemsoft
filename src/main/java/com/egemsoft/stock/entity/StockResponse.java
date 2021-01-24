package com.egemsoft.stock.entity;

import lombok.Data;

import java.util.List;

/**
 * stock details Response of webAPI
 */
@Data
public class StockResponse {
    /**
     * code of message
     */
    private int code;
    /**
     * body of message
     */
    private Stock data;
}
