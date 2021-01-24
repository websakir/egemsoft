package com.egemsoft.stock.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * calculated stocks alis sums dto object
 */
@Data
public class ThreadSumDTO {

    private BigDecimal totalSum;
    private BigDecimal thread1Sum;
    private BigDecimal thread2Sum;
    private BigDecimal thread3Sum;
    private BigDecimal thread4Sum;
    private BigDecimal thread5Sum;

    public void increaseTotalSum(BigDecimal amount){
        if(totalSum == null){
            totalSum = new BigDecimal(0);
        }
        totalSum = totalSum.add(amount);
    }
}
