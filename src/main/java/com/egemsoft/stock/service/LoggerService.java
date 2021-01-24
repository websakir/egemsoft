package com.egemsoft.stock.service;

import com.egemsoft.stock.dto.EndPointResponseDTO;
import com.egemsoft.stock.entity.Logger;

import java.util.List;

public interface LoggerService {

    Logger saveVisitor(Logger logger);

    EndPointResponseDTO getEndPointLog();

}
