package com.stefan.OnlineShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String shippingAddress;
    @Column(name="orderDate")
    private LocalDate orderDate;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderProduct> orderProducts;


}
