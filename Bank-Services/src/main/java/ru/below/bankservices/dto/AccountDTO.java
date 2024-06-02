package ru.below.bankservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Schema(description = "Сущность для работы с банковским аккаунтом")
public class AccountDTO {
    private Long userId;
    private BigDecimal amountOfTheCharge;
}
