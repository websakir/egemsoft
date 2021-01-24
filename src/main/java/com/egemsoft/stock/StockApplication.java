package com.egemsoft.stock;

import com.egemsoft.stock.service.StockJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This application written for egemsoft company. This is interview acceptence task
 * @author Shakir Gulmammadov
 * @version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);


	}

}
