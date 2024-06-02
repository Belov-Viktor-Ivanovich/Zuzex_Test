package ru.below.bankservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сущность для пополнения баланса")
public class UserDTO {
    private Long id;
    private String name;
    private String password;
}
