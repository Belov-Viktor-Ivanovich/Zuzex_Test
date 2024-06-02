package ru.below.stockservices.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class OrderDTO {
        private Long id;
        private Long userId;
        private Long productId;
        private BigDecimal price;
        private Long quantity;
        private String description;
}
