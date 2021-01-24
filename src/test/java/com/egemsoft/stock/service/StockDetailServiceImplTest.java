package com.egemsoft.stock.service;

import com.egemsoft.stock.dto.StockDetailDTO;
import com.egemsoft.stock.dto.ThreadSumDTO;
import com.egemsoft.stock.entity.StockDetail;
import com.egemsoft.stock.repository.CompanyRepository;
import com.egemsoft.stock.repository.StockDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockDetailServiceImplTest {


    @Autowired
    private StockDetailService stockDetailService;

    @MockBean
    private StockDetailRepository stockDetailRepository;

    @Test
    void saveStockDetail() {

        //Setup our mock repository
        StockDetail stockDetail = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        Mockito.doReturn(stockDetail).when(stockDetailRepository).save(stockDetail);

        //Execute the service save method
        StockDetail returnedStockDetail1 = stockDetailService.saveStockDetail(stockDetail);

        //Assert thre response
        Assertions.assertNotNull(returnedStockDetail1, "The saved widget should not be null");
        Assertions.assertEquals(39, returnedStockDetail1.getId(), "The id should be incremented");
        Assertions.assertEquals(53, returnedStockDetail1.getSembolid(), "The sembol id should be "+stockDetail.getSembolid());


    }

    @Test
    void getAllStockDetailsAlisSum() {

        //Setup our mock repository
        StockDetail stockDetail = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai2 = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai3 = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai4 = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai5 = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        Mockito.doReturn(Arrays.asList(stockDetail,stockDetai2, stockDetai3, stockDetai4, stockDetai5)).when(stockDetailRepository).findAll();

        //Execute the service save method
        ThreadSumDTO returnedStockDetails = stockDetailService.getAllStockDetailsAlisSum();

        //Assert thre response
        Assertions.assertNotNull(returnedStockDetails, "The saved widget should not be null");
        Assertions.assertEquals(returnedStockDetails.getTotalSum(),
                returnedStockDetails.getThread1Sum()
                .add(returnedStockDetails.getThread2Sum())
                .add(returnedStockDetails.getThread3Sum())
                .add(returnedStockDetails.getThread4Sum())
                .add(returnedStockDetails.getThread5Sum()), "total Sum should be equal sum of thread sums");

    }

    @Test
    void getStockDetailByKod() {

        //Setup our mock repository
        StockDetail stockDetail = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        Mockito.doReturn(stockDetail).when(stockDetailRepository).findBySembolAndState(stockDetail.getSembol(), 0);

        //Execute the service save method
        StockDetail returnedStockDetail1 = stockDetailService.getStockDetailByKod(stockDetail.getSembol());

        //Assert thre response
        Assertions.assertNotNull(returnedStockDetail1, "The saved widget should not be null");
        Assertions.assertEquals(stockDetail.getId(), returnedStockDetail1.getId(), "The id should be incremented");
        Assertions.assertEquals(stockDetail.getSembolid(), returnedStockDetail1.getSembolid(), "The sembol id should be "+stockDetail.getSembolid());




    }

    @Test
    void getHistoryStockDetailByKod() {

        //Setup our mock repository
        StockDetail stockDetail = new StockDetail(39, 53, "ATAGY", 1, 2, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai2 = new StockDetail(39, 53, "ATAGY", 1, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai3 = new StockDetail(39, 53, "ATAGY", 1, -1, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai4 = new StockDetail(39, 53, "ATAGY", 1, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai5 = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        Mockito.doReturn(Arrays.asList(stockDetail,stockDetai2,stockDetai3,stockDetai4,stockDetai5)).when(stockDetailRepository).findHistoryBySembol(stockDetail.getSembol());

        //Execute the service save method
        List<StockDetail> returnedStockDetail1 = stockDetailService.getHistoryStockDetailByKod(stockDetail.getSembol());

        //Assert thre response
        Assertions.assertNotNull(returnedStockDetail1, "The saved widget should not be null");
        Assertions.assertEquals(5, returnedStockDetail1.size(), "Count of returned stock details should be 5");
        Assertions.assertEquals(stockDetai2.getSembolid(), returnedStockDetail1.get(1).getSembolid(), "The sembol id should be "+stockDetail.getSembolid());


    }

    @Test
    void getStockDetailArtisAzalis() {

        //Setup our mock repository
        StockDetail stockDetail = new StockDetail(39, 53, "ATAGY", 1, 2, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai2 = new StockDetail(39, 53, "ATAGY", 1, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai3 = new StockDetail(39, 53, "ATAGY", 1, -1, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai4 = new StockDetail(39, 53, "ATAGY", 1, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetai5 = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);

        Mockito.doReturn(Arrays.asList(stockDetail,stockDetai2,stockDetai3,stockDetai4,stockDetai5)).when(stockDetailRepository).findAllByArtisByDesc();
        Mockito.doReturn(Arrays.asList(stockDetai5,stockDetai4,stockDetai3,stockDetai2,stockDetail)).when(stockDetailRepository).findAllByAzalisByDesc();

        //Execute the service save method
        Map<String, List<StockDetailDTO>> returnedStockDetail1 = stockDetailService.getStockDetailArtisAzalis();

        //Assert thre response
        Assertions.assertNotNull(returnedStockDetail1, "The saved stock should not be null");
        Assertions.assertEquals(2, returnedStockDetail1.size(), "Count of returned stock details should be 5");
        Assertions.assertEquals(5, returnedStockDetail1.get("inc").size(), "The sembol id should be "+stockDetail.getSembolid());
        Assertions.assertEquals(5, returnedStockDetail1.get("dec").size(), "The sembol id should be "+stockDetail.getSembolid());




    }

}