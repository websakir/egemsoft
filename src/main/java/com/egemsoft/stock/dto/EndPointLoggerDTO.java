package com.egemsoft.stock.dto;

import com.egemsoft.stock.entity.enums.Direction;
import com.egemsoft.stock.entity.enums.ReqMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * called api log dto object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndPointLoggerDTO {

    private String endPointName;
    private Date eventDate;
    private ReqMethod method;
    private Direction direction;
    private String header;
    private String body;

}
