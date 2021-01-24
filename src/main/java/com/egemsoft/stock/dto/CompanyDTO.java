package com.egemsoft.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * company dto object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private int id;
    private String kod;
    private String ad;
    private String tip;
}
