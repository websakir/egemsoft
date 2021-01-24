package com.egemsoft.stock.service;

import com.egemsoft.stock.dto.EndPointLoggerDTO;
import com.egemsoft.stock.dto.EndPointResponseDTO;
import com.egemsoft.stock.entity.Logger;
import com.egemsoft.stock.entity.enums.Direction;
import com.egemsoft.stock.entity.enums.ReqMethod;
import com.egemsoft.stock.repository.LoggerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;


@SpringBootTest
class LoggerServiceImplTest {

    @Autowired
    private LoggerService loggerService;

    @MockBean
    private LoggerRepository loggerRepository;


    @Test
    void saveVisitor() {
        //setup mock repository
        Logger requestLogger = new Logger("/api/v1/hisseler/", new Date(), ReqMethod.GET, Direction.REQUEST, "", "");
        Mockito.doReturn(requestLogger).when(loggerRepository).save(requestLogger);

        //Execute the service save method
        Logger returnedLogger = loggerService.saveVisitor(requestLogger);

        //Assert thre response
        Assertions.assertNotNull(returnedLogger, "The saved logger should not be null");
        Assertions.assertEquals(requestLogger.getId(), returnedLogger.getId(), "The id should be incremented");

    }

    @Test
    void getEndPointLog() {

        //Setup our mock service
        Logger requestLogger = new Logger("/api/v1/hisseler/", new Date(), ReqMethod.GET, Direction.REQUEST, "", "");
        Logger responseLogger = new Logger("/api/v1/hisseler/", new Date(), ReqMethod.GET, Direction.RESPONSE, "", "");
        EndPointLoggerDTO responseEndPointLoggerDTO = new EndPointLoggerDTO("/api/v1/hisseler/", new Date(), ReqMethod.GET, Direction.RESPONSE, "", "");

        Mockito.doReturn(Arrays.asList(requestLogger, responseLogger)).when(loggerRepository).findAll();

        //Execute the service save method
        EndPointResponseDTO returnedEndPointResponseDTO = loggerService.getEndPointLog();

        //Assert thre response
        Assertions.assertNotNull(returnedEndPointResponseDTO, "The saved requestEndPointLoggerDTO should not be null");
        Assertions.assertEquals(1, returnedEndPointResponseDTO.getCount(), "The count should be same");



    }


}