package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.StockDetail;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * calculating sum of given arrays
 */
public class PartialSum implements Callable<Double> {

    private double localSum;
    private List<StockDetail> stockDetails;

    public PartialSum(List<StockDetail> stockDetails) {

        this.stockDetails = stockDetails;
    }

    /**
     * calling seperatly from main thread
     * @return sum of alish prices from given list
     */
    @Override
    public Double call() {

        for (int i =0; i<stockDetails.size(); i++){

            localSum = localSum+stockDetails.get(i).getAlis();
        }
        return localSum;
    }
}
