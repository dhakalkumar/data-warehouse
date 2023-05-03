package com.example.datawarehouse.controller;

import com.example.datawarehouse.dto.DealRequestDto;
import com.example.datawarehouse.exception.DealAlreadyExistsException;
import com.example.datawarehouse.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DealController {
    
    private final DealService dealService;
    
    /**
     * Save a list of deals to the database
     *
     * @param dealRequests The deals to be saved
     * @return success or failure message
     */
    @PostMapping("/deals")
    public ResponseEntity<String> saveDeals(@Valid @RequestBody List<DealRequestDto> dealRequests) {
        try {
            dealService.saveDeals(dealRequests);
        } catch (DealAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}