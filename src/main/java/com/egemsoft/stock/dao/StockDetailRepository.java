package com.egemsoft.stock.dao;

import com.egemsoft.stock.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StockDetailRepository  extends JpaRepository<StockDetail, Integer> {

    StockDetail findByStateAndSembolid(int state, int sembolid);

    @Query(value = "select s.* from db.stock_details s where s.tarih = (select max(tarih) from db.stock_details where sembol = ?1) and s.sembol=?1", nativeQuery = true)
    StockDetail findLastBySembol(String kod);

    @Query(value = "select * from db.stock_details where sembol = ?1 order by tarih desc", nativeQuery = true)
    List<StockDetail> findHistoryBySembol(String kod);

    List<StockDetail> findAllByStateOrderByIncreaseAsc(int satte);

    List<StockDetail> findAllByStateOrderByIncreaseDesc(int satte);

}
