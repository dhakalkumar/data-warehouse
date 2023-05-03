package com.example.datawarehouse.service;

import com.example.datawarehouse.dto.DealRequestDto;
import com.example.datawarehouse.entity.Deal;
import com.example.datawarehouse.exception.DealAlreadyExistsException;

import java.util.List;

public interface DealService {
    
    List<Deal> saveDeals(List<DealRequestDto> dealRequests) throws DealAlreadyExistsException;
    
}
