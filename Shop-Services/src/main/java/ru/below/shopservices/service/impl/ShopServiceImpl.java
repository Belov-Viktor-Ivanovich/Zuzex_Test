package ru.below.shopservices.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.below.shopservices.dto.AccountDTO;
import ru.below.shopservices.dto.OrderDTO;
import ru.below.shopservices.dto.ProductDTO;
import ru.below.shopservices.service.ShopService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    @Autowired
    private final RestTemplate restTemplate;
    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject("http://STOCK-SERVICES/product/" + id, ProductDTO.class));
    }
    @Override
    public List<ProductDTO> findAllProducts() {
        return restTemplate.getForObject("http://STOCK-SERVICES/product", List.class);
    }
    @Override
    public String purchaseProduct(String username, Long productId, Long quantity) {
        ProductDTO product;
        Long userId;
        Boolean writingOffMoney;
        //нужно ли это, или клиент сказу передает свой ID
        try {
            userId = restTemplate.getForObject("http://IDENTITY-SERVICES/api/v1/auth/getUserId/" + username, Long.class);
        } catch (Exception e) {
            return "Пользователь указан не верно";
        }
        //
        try {
            product = restTemplate.getForObject("http://STOCK-SERVICES/product/" + productId, ProductDTO.class);
        } catch (Exception e) {
            return "Id продукта указан не верно";
        }

        if (product.getQuantity() >= quantity) {

            var result = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            var accountDTO = AccountDTO.builder().userId(userId).amountOfTheCharge(result).build();
            try {
                writingOffMoney = restTemplate.postForObject("http://BANK-SERVICES/account/writingOffMoney", accountDTO, Boolean.class);
            } catch (Exception e) {
                return "Транзакция денег не удалась";
            }
            if (Boolean.TRUE.equals(writingOffMoney)) {
                try {
                    var otderDTO = OrderDTO.builder().userId(userId).productId(product.getId()).quantity(quantity).build();
                    return restTemplate.postForObject("http://STOCK-SERVICES/product/reservation", otderDTO, String.class);
                } catch (Exception e) {
                    return "Заказ не был сформирован, обратитесь в службу поддержки";
                }
            } else {
                return "Транзакция не прошла, возможно не хватает денежных средств";
            }
        }
        return "Доступное количество товара меньше требуемой";
    }
}
