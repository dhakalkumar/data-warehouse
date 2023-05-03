package com.example.datawarehouse.service;

import com.example.datawarehouse.dto.DealRequestDto;
import com.example.datawarehouse.entity.Deal;
import com.example.datawarehouse.exception.DealAlreadyExistsException;
import com.example.datawarehouse.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    
    private final DealRepository dealRepository;
    
    @Transactional(rollbackFor = Exception.class)
    public List<Deal> saveDeals(List<DealRequestDto> dealRequests) throws DealAlreadyExistsException {
        List<Deal> savedDeals = new ArrayList<>();
        for (DealRequestDto dealRequest : dealRequests) {
            // check if the deal id already exists
            if (dealRepository.findByDealId(dealRequest.getDealId()).isPresent()) {
                log.error("Deal with given ID already exists!");
                throw new DealAlreadyExistsException("Deal with ID '" + dealRequest.getDealId() + "' already exists");
            }
            
            Deal deal = new Deal().builder()
                    .dealId(dealRequest.getDealId())
                    .dealAmount(dealRequest.getDealAmount())
                    .dealTimestamp(LocalDateTime.now())
                    .fromCurrencyIsoCode(dealRequest.getFromCurrencyISOCode())
                    .toCurrencyIsoCode(dealRequest.getToCurrencyISOCode())
                    .build();
    
            savedDeals.add(dealRepository.save(deal));
        }
        log.info("Saved the deals successfully");
        return savedDeals;
    }
    
}
