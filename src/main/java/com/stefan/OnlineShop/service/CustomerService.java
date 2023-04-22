package com.stefan.OnlineShop.service;

import com.stefan.OnlineShop.domain.Customer;
import com.stefan.OnlineShop.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
