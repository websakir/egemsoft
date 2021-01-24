package com.egemsoft.stock.service;

import com.egemsoft.stock.repository.CompanyRepository;
import com.egemsoft.stock.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Business logic of companies
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    Logger logger =  LoggerFactory.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * saving companies
     *
     * Big(O) complexity O(1) or O(N) it depends on db algorithm
     *
     * @param company the company object which needs save
     */
    @Override
    @Transactional
    public Company saveCompany(Company company) {
       return companyRepository.save(company);
    }

    /**
     *
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * getting companies list by paging
     * @param paging size of page and ordenumberr
     * @return list of proper comnies
     */
    @Override
    public Page<Company> findAllCompanies(Pageable paging) {
        logger.info("getting companies with "+paging);
        return  companyRepository.findAll(paging);
    }

    /**
     * getting concreate company
     *
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * @param kod name of company
     * @param paging number of page size and position
     * @return list of companis
     */
    @Override
    public Page<Company> findAllCompanies(String kod, Pageable paging) {
        logger.info("getting companies with "+kod+" and "+paging);
        return companyRepository.findByKodContaining(kod, paging);
    }
}
