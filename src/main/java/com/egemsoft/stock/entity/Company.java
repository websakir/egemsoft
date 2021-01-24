package com.egemsoft.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Company entity mapped to db table
 */
@Entity
@Data
@Table(name="companies")
@AllArgsConstructor
@NoArgsConstructor
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
