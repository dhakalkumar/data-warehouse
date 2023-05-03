package com.example.datawarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fx_deals")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "deal_id")
    private String dealId;
    
    @Column(name = "from_currency_iso_code")
    private String fromCurrencyIsoCode;
    
    @Column(name = "to_currency_iso_code")
    private String toCurrencyIsoCode;
    
    @Column(name = "deal_date_time")
    private LocalDateTime dealTimestamp;
    
    @Column(name = "deal_amount")
    private BigDecimal dealAmount;
    
}




