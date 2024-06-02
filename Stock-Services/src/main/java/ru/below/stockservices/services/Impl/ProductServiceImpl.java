package ru.below.stockservices.services.Impl;

import com.netflix.discovery.shared.Pair;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.below.stockservices.dto.OrderDTO;
import ru.below.stockservices.entity.Order;
import ru.below.stockservices.entity.Product;
import ru.below.stockservices.kafka.KafkaProducer;
import ru.below.stockservices.repository.OrderRepository;
import ru.below.stockservices.repository.ProductRepository;
import ru.below.stockservices.services.ProductServise;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductServise {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;
    @Override
    @Cacheable
    public Optional<Product> getProduct(Long id) {
        System.out.println("cache data");
       return productRepository.findProductById(id);
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Cacheable
    public List<Product> getAllProducts() {
        System.out.println("cache data");
        return productRepository.findAllBy();
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public String reserveProduct(OrderDTO orderDTO) {
        var userId = orderDTO.getUserId();
        var productId = orderDTO.getProductId();
        var product = productRepository.findProductById(productId).get();
        product.setQuantity(product.getQuantity() - orderDTO.getQuantity());
        productRepository.save(product);
        var order = Order.builder()
                .productId(productId)
                .userId(userId)
                .description("reserved")
                .price(product.getPrice())
                .quantity(orderDTO.getQuantity())
                .build();
        orderRepository.save(order);
        kafkaProducer.sendMessage("Заказ пользователя с "+orderDTO.getUserId()+" успешно сформирован");
        System.out.println("cache data update");
        return "successfully";
    }

}
