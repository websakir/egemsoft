package com.egemsoft.stock.service;

import com.egemsoft.stock.dto.EndPointLoggerDTO;
import com.egemsoft.stock.dto.EndPointResponseDTO;
import com.egemsoft.stock.entity.Logger;
import com.egemsoft.stock.repository.LoggerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic of called apis logging
 */
@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    LoggerRepository loggerRepository;

    /**
     * saving used apis details
     * Big(O) complexity O(1) or O(N)
     * @param logger persisting called api
     */
    @Override
    @Transactional
    public Logger saveVisitor(Logger logger) {
       return loggerRepository.save(logger);
    }

    /**
     * getting Endpoint logs, decoding header and bodies and mapping to dto object
     *
     * Big(O) complexity O(N)
     *
     * @return list of called apis
     */
    @Override
    public EndPointResponseDTO getEndPointLog() {

        //getting all called apis
        List<Logger> loggers = loggerRepository.findAll();
        List<EndPointLoggerDTO> endPointLoggerDTOs;
        ModelMapper modelMapper = new ModelMapper();

        //decoding header and bodies, mapping o dto class
        endPointLoggerDTOs = loggers.stream().map(n -> {
            n.setHeader(getDecod(n.getHeader()));
            n.setBody(getDecod(n.getBody()));
            return modelMapper.map(n, EndPointLoggerDTO.class);
        }).collect(Collectors.toList());

        //wrapping dto to response object
        EndPointResponseDTO endPointResponseDTO = new EndPointResponseDTO();
        endPointResponseDTO.setCount(endPointLoggerDTOs.size()/2);
        endPointResponseDTO.setEndPointLoggerDTOList(endPointLoggerDTOs);

        return endPointResponseDTO;
    }

    /**
     * decoding to base64
     *
     * Big(O) complexity O(1)
     *
     * @param text header and body
     * @return decoded message
     */
    public String getDecod(String text) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(text));
    }

}
