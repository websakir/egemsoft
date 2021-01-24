package com.egemsoft.stock.service;

import com.egemsoft.stock.dto.StockDetailDTO;
import com.egemsoft.stock.dto.ThreadSumDTO;
import com.egemsoft.stock.entity.StockDetail;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public interface StockDetailService {

    StockDetail saveStockDetail(StockDetail stockDetail);

    ThreadSumDTO getAllStockDetailsAlisSum();

    StockDetail getStockDetailByKod(String sembol);

    List<StockDetail> getHistoryStockDetailByKod(String sembol);

    Map<String, List<StockDetailDTO>> getStockDetailArtisAzalis();
}
