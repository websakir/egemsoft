package com.egemsoft.stock.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="stock_details")
public class StockDetail {
    @Id
    private int id;
    private int sembolid;
    private String sembol;
    private LocalDateTime tarih;
    private int sektorid;
    private float alis;
    private float satis;
    private float acilis;
    private float yuksek;
    private float yukseK1;
    private float yukseK2;
    private float dusuk;
    private float dusuK1;
    private float dusuK2;
    private float kapanis;
    private float kapaniS1;
    private float kapaniS2;
    private int hacimlot;
    private int hacimloT1;
    private int hacimloT2;
    private float aort;
    private float aorT1;
    private float aorT2;
    private long hacimtldun;
    private int hacimtl;
    private int hacimtL1;
    private int hacimtL2;
    private float hacimyuzdedegisim;
    private float dunkukapanis;
    private float oncekikapanis;
    private float izafikapanis;
    private float tavan;
    private float taban;
    private float yilyuksek;
    private float yildusuk;
    private float ayyuksek;
    private float aydusuk;
    private float haftayuksek;
    private float haftadusuk;
    private float oncekiyilkapanis;
    private float oncekiaykapanis;
    private float oncekihaftakapanis;
    private float yilortalama;
    private double ayortalama;
    private double haftaortalama;
    private float yuzdedegisimS1;
    private float yuzdedegisimS2;
    private float yuzdedegisim;
    private float fiyatadimi;
    private int kaykar;
    private long sermaye;
    private float saklamaor;
    private long netkar;
    private float net;
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
