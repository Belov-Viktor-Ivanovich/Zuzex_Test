package ru.below.stockservices.services.Impl;

import com.netflix.discovery.shared.Pair;
import lombok.RequiredArgsConstructor;
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
public class ProductServiceImpl implements ProductServise {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;
    @Override
    public Optional<Product> getProduct(Long id) {
       return productRepository.findProductById(id);
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllBy();
    }

    @Override
    @Transactional
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
        return "successfully";
    }

}
