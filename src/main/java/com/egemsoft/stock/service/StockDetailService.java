package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.ChangeDiagram;
import com.egemsoft.stock.entity.StockDetail;

import java.util.List;
import java.util.Map;


public interface StockDetailService {

    void saveStockDetail(StockDetail stockDetail);

    long getAllStockDetailsAlisSum();

    StockDetail getStockDetailByKod(String sembol);

    List<ChangeDiagram> getHistoryStockDetailByKod(String sembol);

    Map<String, List<StockDetail>> getStockDetailGraph();
}
