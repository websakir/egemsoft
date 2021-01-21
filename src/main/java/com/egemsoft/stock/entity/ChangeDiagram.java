package com.egemsoft.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class ChangeDiagram {

    private int id;
    private String sembol;
    double percentage;
    double alis;
    Date eventTime;
}