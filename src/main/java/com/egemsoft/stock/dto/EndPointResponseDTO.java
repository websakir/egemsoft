package com.egemsoft.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * list of used APIs head, body, method and direction
 * And count of them
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndPointResponseDTO {

    private int count;
    private List<EndPointLoggerDTO> endPointLoggerDTOList;

}
