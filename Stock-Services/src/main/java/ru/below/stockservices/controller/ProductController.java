package ru.below.stockservices.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.below.stockservices.dto.OrderDTO;
import ru.below.stockservices.entity.Product;
import ru.below.stockservices.services.Impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "ProductController", description = "Для работа со складом")
public class ProductController {
    private final ProductServiceImpl productServise;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Позволяет получить продукт по id")
    public Optional<Product> getProduct(@PathVariable Long id) {
       return productServise.getProduct(id);
    }
    @PostMapping
    @Operation(description = "Позволяет добавлять продукты")
    public Product addProduct(@RequestBody Product product) {
        return productServise.addProduct(product);
    }
    @GetMapping("/getPrice/{productId}")
    @Operation(description = "Позволяет получить цену продукта")
    public BigDecimal getPrice(@PathVariable Long productId) {
        return  productServise.getProduct(productId).get().getPrice();
    }
    @GetMapping
    @Operation(description = "Позволяет получить все продукты")
    public List<Product> getAllProducts() {
        return productServise.getAllProducts();
    }
    @PostMapping("/reservation")
    public String reserveProduct(@RequestBody OrderDTO orderDTO) {
        return productServise.reserveProduct(orderDTO);
    }

}
