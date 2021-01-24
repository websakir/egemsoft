package com.egemsoft.stock.controller;

import com.egemsoft.stock.dto.EndPointLoggerDTO;
import com.egemsoft.stock.dto.EndPointResponseDTO;
import com.egemsoft.stock.entity.Logger;
import com.egemsoft.stock.entity.enums.Direction;
import com.egemsoft.stock.entity.enums.ReqMethod;
import com.egemsoft.stock.service.LoggerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = "com.egemsoft.stock")
@SpringBootTest
@AutoConfigureMockMvc
class ReportControllerTest {

    @MockBean
    private LoggerService loggerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
   void  getReport() throws Exception {

       //Setup our mock service
       EndPointLoggerDTO requestEndPointLoggerDTO = new EndPointLoggerDTO("/api/v1/hisseler/", new Date(), ReqMethod.GET, Direction.REQUEST, "", "");
       EndPointLoggerDTO responseEndPointLoggerDTO = new EndPointLoggerDTO("/api/v1/hisseler/", new Date(), ReqMethod.GET, Direction.RESPONSE, "", "");

       EndPointResponseDTO endPointResponseDTO = new EndPointResponseDTO(1, Arrays.asList(requestEndPointLoggerDTO, responseEndPointLoggerDTO));


       Mockito.doReturn(endPointResponseDTO).when(loggerService).getEndPointLog();

       mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/report"))

               //Validate response code and content type
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

               //Validate the returned fields
               .andExpect(MockMvcResultMatchers.jsonPath("$.count", Matchers.is(1)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.endPointLoggerDTOList[0].endPointName", Matchers.is("/api/v1/hisseler/")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.endPointLoggerDTOList[0].direction", Matchers.is("REQUEST")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.endPointLoggerDTOList[1].endPointName", Matchers.is("/api/v1/hisseler/")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.endPointLoggerDTOList[1].direction", Matchers.is("RESPONSE")));



   }

}