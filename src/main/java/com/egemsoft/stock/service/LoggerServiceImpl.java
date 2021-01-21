package com.egemsoft.stock.service;

import com.egemsoft.stock.dao.LoggerRepository;
import com.egemsoft.stock.entity.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    LoggerRepository loggerRepository;

    @Override
    public void saveVisitor(Logger logger) {
     loggerRepository.save(logger);
    }
}
