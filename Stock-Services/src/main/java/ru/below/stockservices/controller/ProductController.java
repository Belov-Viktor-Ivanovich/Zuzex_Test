package ru.below.stockservices.controller;

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
public class ProductController {
    private final ProductServiceImpl productServise;

    /*@Autowired
    private JsonKafkaProducer jsonKafkaProducer;
    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody OrderDTO orderDTO) {
        jsonKafkaProducer.sendMessage(orderDTO);
        return ResponseEntity.ok("Order published");
    }*/
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> getProduct(@PathVariable Long id) {
       return productServise.getProduct(id);
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productServise.addProduct(product);
    }
    //не надо
    @GetMapping("/getPrice/{productId}")
    public BigDecimal getPrice(@PathVariable Long productId) {
        return  productServise.getProduct(productId).get().getPrice();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productServise.getAllProducts();
    }
    @PostMapping("/reservation")
    public String reserveProduct(@RequestBody OrderDTO orderDTO) {
        return productServise.reserveProduct(orderDTO);
    }

}
