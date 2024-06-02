package ru.below.shopservices.service;

import ru.below.shopservices.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    Optional<ProductDTO> findProductById(Long id);
    List<ProductDTO> findAllProducts();
    String purchaseProduct(String username, Long productId, Long quantity);
}
