package ru.below.bankservices.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class AccountDTO {
    private Long userId;
    private BigDecimal amountOfTheCharge;
}
