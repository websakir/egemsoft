package com.egemsoft.stock.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="companies")
public class Company {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="kod")
    private String kod;

    @Column(name="ad")
    private String ad;

    @Column(name="tip")
    private String tip;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
//    private List<StockDetail> stockDetailList;
}
