package com.egemsoft.stock.controller;

import com.egemsoft.stock.dto.ThreadSumDTO;
import com.egemsoft.stock.service.LoggerService;
import com.egemsoft.stock.service.StockDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * rest controller for  mutitherding demo sum calculation
 */

@RestController
@RequestMapping("/api/v1/thread-demo")
public class DemoController {

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    StockDetailService stockDetailService;

    /**
     * calculates stocks alis prices in a paralell time for certain groups and sum of them
     *
     * Big(O) complexity O(N)
     *
     * @return sum of stocks alish prices seperated by equal pieces and sum of them
     */
    @GetMapping
    public ResponseEntity<ThreadSumDTO> getThreadDemo() {
        ResponseEntity<ThreadSumDTO> threadSumDTOResponseEntity = new ResponseEntity<>(stockDetailService.getAllStockDetailsAlisSum(), HttpStatus.OK);
        logger.info(threadSumDTOResponseEntity.toString());
        return  threadSumDTOResponseEntity;
    }

}
