package ru.below.shopservices.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.below.shopservices.dto.ProductDTO;
import ru.below.shopservices.service.impl.ShopServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
@Tag(name = "ShopController")
public class ShopController {
    private final ShopServiceImpl shopServiceImpl;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Позволяет получить продукт по id")
    public Optional<ProductDTO> findProductById(@PathVariable Long id) {
        return shopServiceImpl.findProductById(id);
    }
    @GetMapping("/products")
    @Operation(description = "Позволяет получить все продукты")
    public List<ProductDTO> findAllProducts() {
        return shopServiceImpl.findAllProducts();
    }
    @PostMapping("/purchase/{userName}/{productId}/{quantity}")
    @Operation(description = "Позволяет забронировать и купить товар")
    public String purchaseProduct(@PathVariable String userName, @PathVariable Long productId,@PathVariable Long quantity) {
        return shopServiceImpl.purchaseProduct(userName,productId,quantity);
    }
}
