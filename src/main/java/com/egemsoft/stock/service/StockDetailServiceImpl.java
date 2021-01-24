package com.egemsoft.stock.service;

import com.egemsoft.stock.dto.StockDetailDTO;
import com.egemsoft.stock.dto.ThreadSumDTO;
import com.egemsoft.stock.entity.StockDetail;
import com.egemsoft.stock.repository.StockDetailRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * StockDetailServiceImpl Service contains any business operations related to Stock
 */
@Service
public class StockDetailServiceImpl implements StockDetailService {

    /**
     * Declaring Stock repository
     */
    @Autowired
    StockDetailRepository stockDetailRepository;

    Logger logger = LoggerFactory.getLogger(StockDetailServiceImpl.class);

    /**
     * count of threding during calculation sum of stocks selling price
     */
    @Value("${egemsoft.threadPool.size}")
    int threadPoolSize;

    /**
     * state of stock detail version
     */
    @Value("${egemsoft.stockDetail.state}")
    private int state;

    /**
     * if stock is not empty then
     * getting last stock from db and comparing dates,
     * if date is divers from each other
     * then change state of last stock of proper company
     * and adding received stock as last
     *
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * @param stockDetail stock should store in db
     */
    @Override
    public StockDetail saveStockDetail(StockDetail stockDetail) {

        if (stockDetail == null) {
            logger.warn("stockDetail is null");
            return null;
        }

        StockDetail currStockDetail = stockDetailRepository.findByStateAndSembolid(0, stockDetail.getSembolid());

        if (currStockDetail == null) {
            return  stockDetailRepository.save(stockDetail);
        }

        if (currStockDetail.getTarih().compareTo(stockDetail.getTarih()) != 0) {
            double changePercentage = calculatePercentage(stockDetail.getAlis(), currStockDetail.getAlis());

            currStockDetail.setState(1);
            stockDetailRepository.save(currStockDetail);
            stockDetail.setIncrease(changePercentage);
            return stockDetailRepository.save(stockDetail);
        }

        return null;
    }

    /**
     * The method devides whole stock list to certain equal pices
     * calculates sum of satis all prices seperatly at the same time.
     * setting this sum of pieces to the dto object (used reflection)
     * total sum the results and ssigning to the dto
     * <p>
     * Big(O) complexity O(N)
     *
     * @return dto object sum of pieces and total sum
     * @throws ExecutionException   in case of any errors getting sum of pieces result from seperated thread
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted, either before or during the activity.
     */
    @Override
    public ThreadSumDTO getAllStockDetailsAlisSum() {

        List<StockDetail> stockDetails = stockDetailRepository.findAll();

        ThreadSumDTO threadSumDTO = new ThreadSumDTO();

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        List<Future<Double>> futureResults = new ArrayList<>();

        int pieces = stockDetails.size() / threadPoolSize;
        for (int i = 0; i < threadPoolSize; i++) {
            int from = pieces * i;
            int to = pieces * (i + 1) - 1;
            PartialSum partialSum = new PartialSum(stockDetails.subList(from, to));
            Future<Double> futureSumResult = executorService.submit(partialSum);
            futureResults.add(futureSumResult);

        }


        BigDecimal totalSum = new BigDecimal(0);
        for (int i = 0; i < futureResults.size(); i++) {
            try {
            BigDecimal amount = new BigDecimal(futureResults.get(i).get(), new MathContext(10, RoundingMode.HALF_DOWN));

                Method method = ThreadSumDTO.class.getMethod("setThread" + (i + 1) + "Sum", BigDecimal.class);
                method.invoke(threadSumDTO, amount);
                totalSum = totalSum.add(amount);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InterruptedException | ExecutionException e) {
                logger.error(e.toString());
            }
        }
        executorService.shutdown();

        threadSumDTO.setTotalSum(totalSum);
        return threadSumDTO;
    }

    /**
     * Getting Stock by kod name
     * <p>
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     *
     * @param kod stocks name
     * @return required Stock by name
     */
    @Override
    public StockDetail getStockDetailByKod(String kod) {
        return stockDetailRepository.findBySembolAndState(kod, 0);
    }

    /**
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     * <p>
     * Getting all stockes for certain kod
     *
     * @param sembol the kod name for certain company
     * @return the list of stockes for required company
     */
    @Override
    public List<StockDetail> getHistoryStockDetailByKod(String sembol) {
        List<StockDetail> stockDetails = stockDetailRepository.findHistoryBySembol(sembol);
        return stockDetails;
    }

    /**
     * Big(O) complexity O(N) or O(log(N)) it depends on db algorithm
     * <p>
     * the method gets  list of increased up than last alish price
     * and decreased down than last alish price
     *
     * @return 2  stock list (increased and decreased listes)
     */
    @Override
    public Map<String, List<StockDetailDTO>> getStockDetailArtisAzalis() {

        Map<String, List<StockDetailDTO>> diagram = new HashMap<>();

        ModelMapper modelMapper = new ModelMapper();
        List<StockDetail> stockDetailsInc =
                stockDetailRepository.findAllByArtisByDesc();
        List<StockDetailDTO> stockDetailDTOsInc =
                stockDetailsInc.stream().map(n -> modelMapper.map(n, StockDetailDTO.class)).collect(Collectors.toList());
        List<StockDetail> stockDetailsDec =
                stockDetailRepository.findAllByAzalisByDesc();
        List<StockDetailDTO> stockDetailDTOsDec =
                stockDetailsDec.stream().map(n -> modelMapper.map(n, StockDetailDTO.class)).collect(Collectors.toList());
        diagram.put("inc", stockDetailDTOsInc);
        diagram.put("dec", stockDetailDTOsDec);
        return diagram;
    }

    /**
     * Big(O) complexity O(1)
     * <p>
     * calculating percentage of alish
     *
     * @param currAlis cureent alish price
     * @param prevAlis previous alish price
     * @return interest rate of alish prices
     */
    public double calculatePercentage(double currAlis, double prevAlis) {
        double diff = currAlis - prevAlis;
        return round((diff / prevAlis) * 100, 6);
    }

    /**
     * Big(O) complexity O(1)
     * <p>
     * rounding double value to certain size
     *
     * @param value  amount which should round
     * @param places size of rounding
     * @return rounded amount
     */
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
