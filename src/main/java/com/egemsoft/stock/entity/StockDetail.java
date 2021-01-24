package com.egemsoft.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * Stock details  entity mapped to db table
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_details")
public class StockDetail {
    @Id
    private int id;
    private int sembolid;
    private String sembol;
    private int state;
    private double increase;
    private Date tarih;
    private int sektorid;
    private double alis;
    private double satis;
    private double acilis;
    private double yuksek;
    private double yukseK1;
    private double yukseK2;
    private double dusuk;
    private double dusuK1;
    private double dusuK2;
    private double kapanis;
    private double kapaniS1;
    private double kapaniS2;
    private int hacimlot;
    private int hacimloT1;
    private int hacimloT2;
    private double aort;
    private double aorT1;
    private double aorT2;
    private long hacimtldun;
    private int hacimtl;
    private int hacimtL1;
    private int hacimtL2;
    private double hacimyuzdedegisim;
    private double dunkukapanis;
    private double oncekikapanis;
    private double izafikapanis;
    private double tavan;
    private double taban;
    private double yilyuksek;
    private double yildusuk;
    private double ayyuksek;
    private double aydusuk;
    private double haftayuksek;
    private double haftadusuk;
    private double oncekiyilkapanis;
    private double oncekiaykapanis;
    private double oncekihaftakapanis;
    private double yilortalama;
    private double ayortalama;
    private double haftaortalama;
    private double yuzdedegisimS1;
    private double yuzdedegisimS2;
    private double yuzdedegisim;
    private double fiyatadimi;
    private int kaykar;
    private long sermaye;
    private double saklamaor;
    private long netkar;
    private double net;
    private int fiyatkaz;
    private long piydeg;
    private double kapanisfark;
    private String donem;
    private long ozsermaye;
    private double beta;
    private String xU100AG;
    private String aciklama;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "sembolid")
//    private Company company;
}
