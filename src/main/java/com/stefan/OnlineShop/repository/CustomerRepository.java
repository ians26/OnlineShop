package com.stefan.OnlineShop.repository;

import com.stefan.OnlineShop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0"+
    "THEN TRUE ELSE FALSE END "+
    "FROM Customer c "+
    "Where c.email = ?1")
    Boolean selectExistsEmail(String email);
}
