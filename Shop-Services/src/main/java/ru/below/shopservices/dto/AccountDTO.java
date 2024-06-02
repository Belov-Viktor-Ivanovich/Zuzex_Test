package ru.below.shopservices.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class AccountDTO {
    private Long userId;
    private BigDecimal amountOfTheCharge;
}
