package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.Company;
import com.egemsoft.stock.entity.CompanyResponse;
import com.egemsoft.stock.entity.Stock;
import com.egemsoft.stock.entity.StockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * The StockJob class scheduled for getting data from external API.
 */

@Component
public class StockJob {

    Logger logger = LoggerFactory.getLogger(StockJob.class.getName());
    /**
     * The value coyains url of companies list
     */
    @Value("${egemsoft.stockListUrl}")
    private String stockListUrl;
    /**
     * The value contains url of stockes of companies
     */
    @Value("${egemsoft.stockDetailUrl}")
    private String stockDetailUrl;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StockDetailService stockDetailService;
    /**
     * Rest Client for calling external APIs
     */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Job method run on start and every on @fixedDelay time
     * Getting companies list, calling stock APIs by compnies and persisting them into db
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
    public void getAllStockes() {
        logger.info("Job Scheduler started at " + new Date());
        logger.debug("hisseler URL " + stockListUrl);
        ResponseEntity<CompanyResponse> responseEntity = restTemplate.exchange(stockListUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<CompanyResponse>() {
                });
        CompanyResponse companyResponse = responseEntity.getBody();

        for (Company company : companyResponse.getData()) {
            companyService.saveCompany(company);
            getStockDetails(company.getKod());
        }
        logger.info("Job Scheduler finsihed at " + new Date());
    }

    /**
     * This method getting stock prices.
     *
     * @param stockKod compny kod for getting its stock prices
     */
    public void getStockDetails(String stockKod) {
        Stock stockDetail = null;
        try {
            ResponseEntity<StockResponse> responseEntity = restTemplate.exchange(stockDetailUrl + stockKod, HttpMethod.GET, null,
                    new ParameterizedTypeReference<StockResponse>() {
                    });
            stockDetail = responseEntity.getBody().getData();
            if (stockDetail.getHisseYuzeysel() == null) {
                logger.warn(stockKod + " IS EMPTY!!!");
                return;
            }
            logger.info(stockDetail.toString());
            stockDetailService.saveStockDetail(stockDetail.getHisseYuzeysel());

        } catch (InvalidDataAccessApiUsageException e) {
            logger.warn(stockKod + " InvalidDataAccessApiUsageException " + e);
        } catch (HttpClientErrorException | IllegalArgumentException exp) {
            logger.warn(stockKod + " Unauthorized request");
        }
    }
}
