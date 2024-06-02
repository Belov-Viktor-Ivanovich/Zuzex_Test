package ru.below.shopservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
}
