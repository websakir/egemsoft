package com.egemsoft.stock.service;

import com.egemsoft.stock.dao.StockDetailRepository;
import com.egemsoft.stock.entity.ChangeDiagram;
import com.egemsoft.stock.entity.StockDetail;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockDetailServiceImpl implements StockDetailService {

    @Autowired
    StockDetailRepository stockDetailRepository;

    @Override
    public void saveStockDetail(StockDetail stockDetail) {
            stockDetailRepository.save(stockDetail);
    }

    @Override
    public StockDetail getStockDetailByKod(String kod) {
    return stockDetailRepository.findLastBySembol(kod);
    }

    @Override
    public List<ChangeDiagram> getHistoryStockDetailByKod(String sembol) {

        List<StockDetail> stockDetails = stockDetailRepository.findHistoryBySembol(sembol);
            List<ChangeDiagram> changeDiagrams = new ArrayList<>();
            for (int i = 1; i <stockDetails.size(); i++){

                double changePercentage = calculatePercentage(stockDetails.get(i).getAlis(), stockDetails.get(i-1).getAlis());
                changeDiagrams.add(new ChangeDiagram(stockDetails.get(i).getId(), stockDetails.get(i).getSembol(),
                        changePercentage, stockDetails.get(i).getAlis(), stockDetails.get(i).getTarih()));

            }

        return changeDiagrams;
    }


    public double calculatePercentage(double currAlis, double prevAlis){
        double diff = currAlis - prevAlis;
        return round(diff/prevAlis, 6)*100;
    }


    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
