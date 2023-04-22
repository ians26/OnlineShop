package com.stefan.OnlineShop.controller;

import com.stefan.OnlineShop.domain.Customer;
import com.stefan.OnlineShop.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer){
        Customer serviceCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(serviceCustomer);
    }
}
