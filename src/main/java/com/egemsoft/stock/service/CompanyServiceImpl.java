package com.egemsoft.stock.service;

import com.egemsoft.stock.dao.CompanyRepository;
import com.egemsoft.stock.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Page<Company> findAllCompanies(Pageable paging) {
        return  companyRepository.findAll(paging);
    }

    @Override
    public Page<Company> findAllCompanies(String kod, Pageable paging) {
        return companyRepository.findByKodContaining(kod, paging);
    }
}
