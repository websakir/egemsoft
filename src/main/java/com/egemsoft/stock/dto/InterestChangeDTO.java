package com.egemsoft.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * stock detail dto object with interest
 */
@Data
public class ChangeDiagramDTO {

    private int id;
    private String sembol;
    double increase;
    double alis;
    Date tarih;
}
