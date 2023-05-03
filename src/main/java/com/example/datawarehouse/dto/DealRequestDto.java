package com.example.datawarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class DealRequestDto {
    
    @NotEmpty(message = "Deal id is required")
    private String dealId;
    
    @NotEmpty(message = "From currency ISO code is required")
    private String fromCurrencyISOCode;
    
    @NotEmpty(message = "To currency ISO code is required")
    private String toCurrencyISOCode;
    
    @NotNull(message = "Deal amount is required")
    private BigDecimal dealAmount;
    
}
