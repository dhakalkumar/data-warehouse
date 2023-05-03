package com.example.datawarehouse;

import com.example.datawarehouse.dto.DealRequestDto;
import com.example.datawarehouse.entity.Deal;
import com.example.datawarehouse.service.DealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DataWarehouseApplicationTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DealService dealService;
    
    @Test
    public void testCreateDeal() throws Exception {
        DealRequestDto dealDto = new DealRequestDto();
        dealDto.setDealId("D123456");
        dealDto.setFromCurrencyISOCode("USD");
        dealDto.setToCurrencyISOCode("EUR");
        dealDto.setDealAmount(new BigDecimal(100));
        
        Deal createdDeal = Deal.builder()
                .id(1L)
                .dealId(dealDto.getDealId())
                .fromCurrencyIsoCode(dealDto.getFromCurrencyISOCode())
                .toCurrencyIsoCode(dealDto.getToCurrencyISOCode())
                .dealAmount(dealDto.getDealAmount())
                .build();
        when(dealService.saveDeals(List.of(dealDto))).thenReturn(List.of(createdDeal));
        
        mockMvc.perform(post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dealDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.dealId", is(dealDto.getDealId())))
                .andExpect(jsonPath("$.fromCurrencyIsoCode", is(dealDto.getFromCurrencyISOCode())))
                .andExpect(jsonPath("$.toCurrencyIsoCode", is(dealDto.getToCurrencyISOCode())))
                .andExpect(jsonPath("$.dealAmount", is(dealDto.getDealAmount().doubleValue())));

        verify(dealService, times(1)).saveDeals(List.of(dealDto));
        verifyNoMoreInteractions(dealService);
    }
    
    @Test
    public void testCreateDealWithMissingFields() throws Exception {
        DealRequestDto dealDto = new DealRequestDto();
        dealDto.setFromCurrencyISOCode("USD");
        dealDto.setToCurrencyISOCode("EUR");
        dealDto.setDealAmount(new BigDecimal(100));
        
        mockMvc.perform(post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dealDto)))
                .andExpect(status().isBadRequest());
    
        verify(dealService, never()).saveDeals(List.of(dealDto));
    }
    
    @Test
    public void testCreateDealWithInvalidFields() throws Exception {
        DealRequestDto dealDto = new DealRequestDto();
        dealDto.setDealId("D123456");
        dealDto.setFromCurrencyISOCode("USD");
        dealDto.setToCurrencyISOCode("EURO");
        dealDto.setDealAmount(new BigDecimal(-100));
        
        mockMvc.perform(post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dealDto)))
                .andExpect(status().isBadRequest());
    
        verify(dealService, never()).saveDeals(List.of(dealDto));
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
