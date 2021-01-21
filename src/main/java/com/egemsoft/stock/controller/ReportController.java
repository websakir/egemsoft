package com.egemsoft.stock.controller;

import com.egemsoft.stock.service.LoggerService;
import com.egemsoft.stock.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    LoggerService loggerService;

    @Autowired
    StockDetailService stockDetailService;

    @GetMapping("/report")
    public void getReport(){


    }

    @GetMapping("/thread-demo")
    public void getthreadDemo(){
    stockDetailService.get

    }

}
