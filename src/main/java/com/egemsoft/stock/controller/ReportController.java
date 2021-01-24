package com.egemsoft.stock.controller;

import com.egemsoft.stock.dto.EndPointResponseDTO;
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
import java.util.concurrent.ExecutionException;


/**
 * rest controller for reporting API requests and responses
 */
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    LoggerService loggerService;

    /**
     * Getting list of called APIs requests
     *
     * Big(O) complexity O(N)
     *
     * @return count and list of called apis
     */
    @GetMapping
    public ResponseEntity<EndPointResponseDTO> getReport() {
        EndPointResponseDTO endPointResponseDTO = loggerService.getEndPointLog();
        logger.info(endPointResponseDTO.getCount()+ " API Requests were sent ");
        return new ResponseEntity<>(endPointResponseDTO, HttpStatus.OK);
    }



}
