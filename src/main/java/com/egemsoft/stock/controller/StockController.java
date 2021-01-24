package com.egemsoft.stock.controller;


import com.egemsoft.stock.dto.InterestChangeDTO;
import com.egemsoft.stock.dto.CompanyDTO;
import com.egemsoft.stock.dto.StockDetailDTO;
import com.egemsoft.stock.entity.Company;
import com.egemsoft.stock.entity.Logger;
import com.egemsoft.stock.entity.StockDetail;
import com.egemsoft.stock.entity.enums.Direction;
import com.egemsoft.stock.entity.enums.ReqMethod;
import com.egemsoft.stock.service.CompanyService;
import com.egemsoft.stock.service.LoggerService;
import com.egemsoft.stock.service.StockDetailService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * Rest Controller for Getting comies List and Stock prices
 *
 */
@RestController
@RequestMapping("/api/v1")
public class StockController {

    @Autowired
    CompanyService companyService;

    @Autowired
    StockDetailService stockDetailService;

    @Autowired
    LoggerService loggerService;

    org.slf4j.Logger logger = LoggerFactory.getLogger(StockController.class);

    /**
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * getting companies list by paging
     * @param header header of request
     * @param sembol required company name
     * @param page order of page
     * @param size size of page
     * @param sort sorting type asc, desc
     * @return list of companies
     */
    @GetMapping("/hisseler/")
    public ResponseEntity<List<CompanyDTO>> getCompanyList(@RequestHeader Map<String, String> header,
                                                           @RequestParam(required = false) String sembol,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam (defaultValue = "5")int size,
                                                           @RequestParam(required = false) String sort) {

        logger.info("/hisseler/ called");
        loggerService.saveVisitor(new Logger("/hisseler/", new Date(), ReqMethod.GET, Direction.REQUEST,
                getEncode(header.toString().getBytes()), ""));

        Order order = null;
        Pageable paging;
        if (sort != null && sort.contains(",")) {
            String[] _sort = sort.split(",");
            if (_sort[0].equals("id")) {
                Sort.Direction direction = _sort[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
                order = new Order(direction, _sort[0]);
            }
            paging = PageRequest.of(page, size, Sort.by(order));
        } else {
            paging = PageRequest.of(page, size);
        }

        List<Company> companyList;
        List<CompanyDTO> companyDTOList;

        Page<Company> pageCompanies;
            if (sembol == null || sembol.isBlank()) {
                pageCompanies = companyService.findAllCompanies(paging);
            } else {
                pageCompanies = companyService.findAllCompanies(sembol, paging);
            }
            companyList = pageCompanies.getContent();
            ModelMapper modelMapper = new ModelMapper();

            companyDTOList = companyList.stream().map(n -> modelMapper.map(n, CompanyDTO.class)).collect(Collectors.toList());



        ResponseEntity<List<CompanyDTO>> responseEntity = new ResponseEntity<>(companyDTOList, HttpStatus.OK);
        loggerService.saveVisitor(new Logger("/hisseler", new Date(), ReqMethod.GET, Direction.RESPONSE,
                getEncode(responseEntity.getHeaders().toString().getBytes()),
                getEncode(responseEntity.getBody().toString().getBytes())));

        return responseEntity;

    }


    /**
     * Getting company stock value
     *
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     * @param header request header
     * @param hisse name of required company
     * @return required compny's last stock data
     */
    @GetMapping("/hisseler/yuzeysel/{hisse}")
    public ResponseEntity<StockDetailDTO> getCompanyStock(@RequestHeader Map<String, String> header,
                                                          @PathVariable String hisse) {

        logger.info("/hisseler/yuzeysel/{hisse} called");
        loggerService.saveVisitor(new Logger("/hisseler/yuzeysel/{hisse}", new Date(), ReqMethod.GET, Direction.REQUEST,
                getEncode(header.toString().getBytes()), ""));

        StockDetail stockDetail = stockDetailService.getStockDetailByKod(hisse);
        ModelMapper mapper = new ModelMapper();
        StockDetailDTO stockDetailDTO = mapper.map(stockDetail, StockDetailDTO.class);
        ResponseEntity<StockDetailDTO> responseEntity = new ResponseEntity<>(stockDetailDTO, HttpStatus.OK);

        loggerService.saveVisitor(new Logger("/hisseler/yuzeysel/{hisse}", new Date(), ReqMethod.GET, Direction.RESPONSE,
                getEncode(responseEntity.getHeaders().toString().getBytes()),
                getEncode(responseEntity.getBody().toString().getBytes())));
        return responseEntity;
    }

    /**
     * Getting all stock histoy for certain company API
     *
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * @param header message header
     * @param hisse name of company
     * @return List of stock details
     */
    @GetMapping("/hisseler/history/{hisse}")
    public ResponseEntity<List<InterestChangeDTO>> getStockHistory(@RequestHeader Map<String, String> header,
                                                                   @PathVariable String hisse) {
        logger.info("/hisseler/history/{hisse} called");
        loggerService.saveVisitor(new Logger("/hisseler/history/{hisse}", new Date(), ReqMethod.GET, Direction.REQUEST,
                getEncode(header.toString().getBytes()), ""));

        List<StockDetail> stockDetails = stockDetailService.getHistoryStockDetailByKod(hisse);

        ModelMapper mapper = new ModelMapper();

        List<InterestChangeDTO> interestChangeDTOs = stockDetails.stream().map(n -> mapper.map(n, InterestChangeDTO.class)).collect(Collectors.toList());
        ResponseEntity<List<InterestChangeDTO>> responseEntity = new ResponseEntity<>(interestChangeDTOs, HttpStatus.OK);

        loggerService.saveVisitor(new Logger("/hisseler/history/{hisse}", new Date(), ReqMethod.GET, Direction.RESPONSE,
                getEncode(responseEntity.getHeaders().toString().getBytes()),
                getEncode(responseEntity.getBody().toString().getBytes())));

        return responseEntity;
    }


    /**
     * Listes of increased and decreased stock interestes
     *
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * @param header message header
     * @return increased interestes stock and decreased interestes stock listes
     */
    @GetMapping("/hisseler/artisazalis")
    public ResponseEntity<Map<String, List<StockDetailDTO>>> getArtisAzalis(@RequestHeader Map<String, String> header) {
        logger.info("/hisseler/artisazalis");
        loggerService.saveVisitor(new Logger("/hisseler/artisazalis", new Date(), ReqMethod.GET, Direction.REQUEST,
                getEncode(header.toString().getBytes()), ""));

        Map<String, List<StockDetailDTO>> diagram = stockDetailService.getStockDetailArtisAzalis();

        ResponseEntity<Map<String, List<StockDetailDTO>>> responseEntity = new ResponseEntity<>(diagram, HttpStatus.OK);

        System.out.println(responseEntity.getBody().toString());
        loggerService.saveVisitor(new Logger("/hisseler/artisazalis", new Date(), ReqMethod.GET, Direction.RESPONSE,
                getEncode(responseEntity.getHeaders().toString().getBytes()),
                getEncode(responseEntity.getBody().toString().getBytes())));

        return responseEntity;
    }


    public String getEncode(byte[] text) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(text);

    }

}
