package com.egemsoft.stock.controller;


import com.egemsoft.stock.entity.ChangeDiagram;
import com.egemsoft.stock.entity.Company;
import com.egemsoft.stock.entity.StockDetail;
import com.egemsoft.stock.service.CompanyService;
import com.egemsoft.stock.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hisseler")
public class StockController {

    @Autowired
    CompanyService companyService;

    @Autowired
    StockDetailService stockDetailService;

@GetMapping("/")
public List<Company> getCompanyList(@RequestParam(required = false) String sembol,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3" ) int size,
                                    @RequestParam(required = false) String sort){

    Order order = null;
    Pageable paging;
    if(sort != null && sort.contains(",")) {
        String[] _sort = sort.split(",");
        if (_sort[0].equals("id")) {
            Sort.Direction direction = _sort[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            order = new Order(direction, _sort[0]);
        }
        paging = PageRequest.of(page, size, Sort.by(order));
    }else{
        paging = PageRequest.of(page, size);
    }

    System.out.println(paging.toString());

    List<Company> companyList = new ArrayList<>();

    Page<Company> pageCompanies;
try {
    if (sembol == null || sembol.equals("")) {
        pageCompanies = companyService.findAllCompanies(paging);
    } else {
        pageCompanies = companyService.findAllCompanies(sembol, paging);
    }
    companyList = pageCompanies.getContent();
}catch (Exception e){
    e.printStackTrace();
}

    return companyList;
}




    @GetMapping("/yuzeysel/{hisse}")
    public StockDetail getCompanyStock(@PathVariable String hisse) {

    StockDetail stockDetail = stockDetailService.getStockDetailByKod(hisse);
        return stockDetail;
    }


    @GetMapping("/history/{hisse}")
    public List<ChangeDiagram> getStockHistory(@PathVariable String hisse) {
        return stockDetailService.getHistoryStockDetailByKod(hisse);
    }
}
