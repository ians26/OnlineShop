package com.stefan.OnlineShop.controller;

import com.stefan.OnlineShop.domain.Product;
import com.stefan.OnlineShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
