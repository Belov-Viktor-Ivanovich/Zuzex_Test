package ru.below.stockservices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Entity
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Min(value = 0, message = "The balance cannot be negative")
    private BigDecimal price;
    @Min(value = 0)
    private Long quantity;
}
