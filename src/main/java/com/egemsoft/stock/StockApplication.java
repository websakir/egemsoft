package com.egemsoft.stock;

import com.egemsoft.stock.service.StockJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockApplication {

	@Autowired
	StockJob stockJob;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);


	}

}
