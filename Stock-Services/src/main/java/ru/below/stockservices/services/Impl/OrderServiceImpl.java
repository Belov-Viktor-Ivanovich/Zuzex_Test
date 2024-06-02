package ru.below.stockservices.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.below.stockservices.entity.Order;
import ru.below.stockservices.repository.OrderRepository;
import ru.below.stockservices.services.OrderService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }
}
