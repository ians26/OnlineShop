package com.stefan.OnlineShop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderProduct {
    @EmbeddedId
    private OrderProductKey orderProductId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;

}
