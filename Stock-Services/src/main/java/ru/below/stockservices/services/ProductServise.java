package ru.below.stockservices.services;
import com.netflix.discovery.shared.Pair;
import ru.below.stockservices.dto.OrderDTO;
import ru.below.stockservices.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductServise {
    Optional<Product> getProduct(Long id);

    Product addProduct(Product product);

    List<Product> getAllProducts();

    String reserveProduct(OrderDTO orderDTO);

}
