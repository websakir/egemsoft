package com.egemsoft.stock.controller;

import com.egemsoft.stock.dto.CompanyDTO;
import com.egemsoft.stock.dto.StockDetailDTO;
import com.egemsoft.stock.entity.Company;
import com.egemsoft.stock.entity.StockDetail;
import com.egemsoft.stock.service.CompanyService;
import com.egemsoft.stock.service.StockDetailService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = "com.egemsoft.stock")
@SpringBootTest
@AutoConfigureMockMvc
class StockControllerTest {

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    StockDetailService stockDetailService;


    @Test
    @DisplayName("GET /hisseler/ success")
    void testGetCompanyList() throws Exception {
        //Setup our mock service
        Company company1 = new Company(1, "ACSEL", "Acıselsan Acıpayam Selüloz Sanayi ve Ticaret A.Ş.", "Hisse");
        Company company2 = new Company(2, "ADEL", "Adel Kalemcilik Ticaret Ve Sanayi A.Ş", "Hisse");
        Company company3 = new Company(3, "ADESE", "Adese Alışveriş Hizmetleri Ticaret A.Ş.", "Hisse");

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(0, 3);
        Mockito.doReturn(new PageImpl<>(Arrays.asList(company1, company2, company3))).when(companyService).findAllCompanies(ArgumentMatchers.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hisseler/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                ;



    }

    @Test
    void getCompanyStock() throws Exception {
        //Setup our mock service
        StockDetail stockDetail = new StockDetail(39, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);

        Mockito.doReturn(stockDetail).when(stockDetailService).getStockDetailByKod("ATAGY");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hisseler/yuzeysel/{hisse}", "ATAGY"))
                //Validate response code and content type
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //Validate the returned fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(39)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sembol", Matchers.is("ATAGY")));

    }

    @Test
    void getStockHistory() throws Exception {
        //Setup our mock service
        StockDetail stockDetail1 = new StockDetail(1, 53, "ATAGY", 1, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetail2 = new StockDetail(2, 53, "ATAGY", 1, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetail stockDetail3 = new StockDetail(3, 53, "ATAGY", 0, 0, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);

        Mockito.doReturn(Arrays.asList(stockDetail1, stockDetail2, stockDetail3)).when(stockDetailService).getHistoryStockDetailByKod("ATAGY");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hisseler/history/{hisse}", "ATAGY"))

                //Validate response code and content type
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].sembol", Matchers.is("ATAGY")));


    }

    @Test
    void getArtisAzalis() throws Exception {

        //Setup our mock service
        StockDetailDTO stockDetail1 = new StockDetailDTO(1, 53, "ATAGY", 1, 1, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetailDTO stockDetail2 = new StockDetailDTO(2, 53, "ATAGY", 1, 1.2, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetailDTO stockDetail3 = new StockDetailDTO(3, 53, "ATAGY", 0, 0.5, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetailDTO stockDetail4 = new StockDetailDTO(3, 53, "ATAGY", 0, -0.2, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetailDTO stockDetail5 = new StockDetailDTO(3, 53, "ATAGY", 0, -0.9, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);
        StockDetailDTO stockDetail6 = new StockDetailDTO(3, 53, "ATAGY", 0, -2, new Date(), 21, 5.96,5.96,5.96,5.96,5.96,5.96,5.96,2,5.96,5.96,5.96,5.96,5,1144410,1144410,1144410,6.06,6.06,6,6927338,6927338,6927338,6927338,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.12,0.1,0,23750000,2,23750000,2,2,1415500000,0,"2020-9", 30937865, 2, "ATA GMYO", null);

        List<StockDetailDTO> artisList = Arrays.asList(stockDetail1, stockDetail2, stockDetail3);
        List<StockDetailDTO> azalisList = Arrays.asList(stockDetail4, stockDetail5, stockDetail6);

        Map<String, List<StockDetailDTO>> listes = new HashMap<>();
        listes.put("artis", artisList);
        listes.put("azalis", azalisList);

        Mockito.doReturn(listes).when(stockDetailService).getStockDetailArtisAzalis();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hisseler/artisazalis"))

                //Validate response code and content type
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.artis", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.azalis", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artis[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artis[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.azalis[1].sembol", Matchers.is("ATAGY")));




    }


}