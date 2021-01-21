package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.Company;
import com.egemsoft.stock.entity.CompanyResponse;
import com.egemsoft.stock.entity.Stock;
import com.egemsoft.stock.entity.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class StockJob {

    private final String stockListUrl = "https://bigpara.hurriyet.com.tr/api/v1/hisse/list";
    private final String stockDetailUrl = "https://bigpara.hurriyet.com.tr/api/v1/borsa/hisseyuzeysel/";
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StockDetailService stockDetailService;
    private RestTemplate restTemplate = new RestTemplate();

//    @Scheduled(initialDelay = 1000, fixedDelay = 180000)
    public void getAllStockes() {

        ResponseEntity<CompanyResponse> responseEntity = restTemplate.exchange(stockListUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<CompanyResponse>() {
                });
        CompanyResponse companyResponse = responseEntity.getBody();

        for (Company company : companyResponse.getData()) {
            companyService.saveCompany(company);
            getStockDetails(company.getKod());
        }

    }

    public void getStockDetails(String stockKod) {
        Stock stockDetail = null;
        try {
            System.out.println(stockKod);
            ResponseEntity<StockResponse> responseEntity = restTemplate.exchange(stockDetailUrl + stockKod, HttpMethod.GET, null,
                    new ParameterizedTypeReference<StockResponse>() {
                    });
            stockDetail = responseEntity.getBody().getData();

            stockDetailService.saveStockDetail(stockDetail.getHisseYuzeysel());

        } catch (InvalidDataAccessApiUsageException e) {
            if (stockDetail == null | stockDetail.getHisseYuzeysel() == null) {
                System.out.println("++++++ stockDetail is null ++++++");
            }
        } catch (HttpClientErrorException | IllegalArgumentException exp) {
            System.out.println(stockKod);
            System.out.println("EXCEPTION" + exp);
        }
    }
}
