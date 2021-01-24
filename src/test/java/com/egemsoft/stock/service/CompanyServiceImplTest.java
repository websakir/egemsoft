package com.egemsoft.stock.service;

import com.egemsoft.stock.entity.Company;
import com.egemsoft.stock.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CompanyServiceImplTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    void saveCompany() {
        // Setup our mock repository
        Company company = new Company(1, "ACSEL", "Acıselsan Acıpayam Selüloz Sanayi ve Ticaret A.Ş.", "Hisse");
        Mockito.doReturn(company).when(companyRepository).save(ArgumentMatchers.any());


        //Execute the service save method
        Company returnedCompany = companyService.saveCompany(company);

        //Assert thre response
        Assertions.assertNotNull(returnedCompany, "The saved widget should not be null");
        Assertions.assertEquals(1, returnedCompany.getId(), "The id should be incremented");
    }

    @Test
    @DisplayName("Test getting all companies")
    void findAllCompanies() {
        //Setup our mock repositories
        Company company1 = new Company(1, "ACSEL", "Acıselsan Acıpayam Selüloz Sanayi ve Ticaret A.Ş.", "Hisse");
        Company company2 = new Company(2, "ADEL", "Adel Kalemcilik Ticaret Ve Sanayi A.Ş", "Hisse");
        Company company3 = new Company(3, "ADESE", "Adese Alışveriş Hizmetleri Ticaret A.Ş.", "Hisse");
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(0, 3, Sort.by(order));
        Mockito.doReturn(new PageImpl<>(Arrays.asList(company1, company2, company3))).when(companyRepository).findAll(paging);

        //Execute the service call
        List<Company> companyList = companyService.findAllCompanies(paging).getContent();

        //Assert the response
        Assertions.assertEquals(3, companyList.size(), "findAll shoult return 3 companies");
        Assertions.assertEquals(company1.getId(), companyList.get(0).getId());


    }

    @Test
    void findAllCompaniesBySembol() {
        //Setup our mock repositories
        Company company1 = new Company(1, "ACSEL", "Acıselsan Acıpayam Selüloz Sanayi ve Ticaret A.Ş.", "Hisse");

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(0, 3, Sort.by(order));
        Mockito.doReturn(new PageImpl<>(Arrays.asList(company1))).when(companyRepository).findByKodContaining(company1.getKod(), paging);

        //Execute the service call
        List<Company> companyList = companyService.findAllCompanies(company1.getKod(), paging).getContent();

        //Assert the response
        Assertions.assertEquals(1, companyList.size(), "findAll shoult return 3 companies");
        Assertions.assertEquals(company1.getId(), companyList.get(0).getId());


    }
}