package com.egemsoft.stock.entity;

import com.egemsoft.stock.entity.converters.DirectionAttributeConverter;
import com.egemsoft.stock.entity.converters.ReqMethodAttributeConverter;
import com.egemsoft.stock.entity.enums.Direction;
import com.egemsoft.stock.entity.enums.ReqMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * called api messages entity mapped to db table
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "loggers")
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String endPointName;
    private Date eventDate;
    @Convert(converter = ReqMethodAttributeConverter.class)
    private ReqMethod method;
    @Convert(converter = DirectionAttributeConverter.class)
    private Direction direction;
    private String header;
    private String body;

    public Logger(String endPointName, Date eventDate, ReqMethod method, Direction direction, String header, String body) {
        this.endPointName = endPointName;
        this.eventDate = eventDate;
        this.method = method;
        this.direction = direction;
        this.header = header;
        this.body = body;
    }

    public Logger(){}
}
