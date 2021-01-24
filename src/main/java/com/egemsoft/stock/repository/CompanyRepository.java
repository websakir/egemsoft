package com.egemsoft.stock.repository;

import com.egemsoft.stock.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Page<Company> findByKodContaining(String kod, Pageable pageable);
}
