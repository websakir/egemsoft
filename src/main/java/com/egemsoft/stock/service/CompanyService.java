package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

    Company saveCompany(Company company);

    Page<Company> findAllCompanies(Pageable paging);

    Page<Company> findAllCompanies(String kod, Pageable paging);
}
