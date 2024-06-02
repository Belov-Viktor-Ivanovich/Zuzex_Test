package ru.below.stockservices.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.below.stockservices.entity.Order;
import ru.below.stockservices.services.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return orderService.findById(id);
    }
}
