package com.egemsoft.stock.entity;

import lombok.Data;

import java.util.List;

@Data
public class CompanyResponse {

    private int code;
    private List<Company> data;
}
