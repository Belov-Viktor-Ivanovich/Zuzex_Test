package ru.below.stockservices.services;

import ru.below.stockservices.entity.Order;

import java.util.Optional;

public interface OrderService {
    Order findById(Long id);
}
