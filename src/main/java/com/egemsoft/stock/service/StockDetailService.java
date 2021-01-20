package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.ChangeDiagram;
import com.egemsoft.stock.entity.StockDetail;

import java.util.List;


public interface StockDetailService {

    void saveStockDetail(StockDetail stockDetail);

    StockDetail getStockDetailByKod(String sembol);

    List<ChangeDiagram> getHistoryStockDetailByKod(String sembol);
}
