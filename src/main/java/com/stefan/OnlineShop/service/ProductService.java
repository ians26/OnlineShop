package com.stefan.OnlineShop.service;

import com.stefan.OnlineShop.domain.Product;
import com.stefan.OnlineShop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
