package ru.below.shopservices.controller;

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
public class ShopController {
    private final ShopServiceImpl shopServiceImpl;
    //наверное не надо
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDTO> findProductById(@PathVariable Long id) {
        return shopServiceImpl.findProductById(id);
    }
    @GetMapping("/products")
    public List<ProductDTO> findAllProducts() {
        return shopServiceImpl.findAllProducts();
    }
    @PostMapping("/purchase/{userName}/{productId}/{quantity}")
    public String purchaseProduct(@PathVariable String userName, @PathVariable Long productId,@PathVariable Long quantity) {
        return shopServiceImpl.purchaseProduct(userName,productId,quantity);
    }
}
