package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    void saveCompany(Company company);

    Page<Company> findAllCompanies(Pageable paging);

    Page<Company> findAllCompanies(String kod, Pageable paging);
}
