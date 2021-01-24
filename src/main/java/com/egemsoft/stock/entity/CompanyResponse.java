package com.egemsoft.stock.entity;

import lombok.Data;

import java.util.List;

/**
 * received compy data from called api
 */
@Data
public class CompanyResponse {

    private int code;
    private List<Company> data;
}
