package ru.below.bankservices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private BigDecimal balance;

    public void addBalance(@NonNull BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void subtractFromBalance(@NonNull BigDecimal amount) {
        var result = this.balance.subtract(amount);
        if (result.signum() < 0) {
            throw new RuntimeException("Недостаточно средств для проведения транзакции.");
        }

        this.balance = result;
    }
}
