package com.egemsoft.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="loggers")
public class Logger {
    public Logger(Date eventDate, String header, String body) {
        this.eventDate = eventDate;
        this.header = header;
        this.body = body;
    }

    @Id
    private int id;
    private Date eventDate;
    private String header;
    private String body;
}
