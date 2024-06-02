package ru.below.stockservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.below.stockservices.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
