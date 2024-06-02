package ru.below.bankservices.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String password;
}
