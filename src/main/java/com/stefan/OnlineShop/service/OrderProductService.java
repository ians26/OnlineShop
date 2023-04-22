package com.stefan.OnlineShop.service;

import com.stefan.OnlineShop.domain.Order;
import com.stefan.OnlineShop.domain.OrderProduct;
import com.stefan.OnlineShop.domain.OrderProductKey;
import com.stefan.OnlineShop.domain.Product;
import com.stefan.OnlineShop.repository.OrderProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public List<OrderProduct> getOrderProducts(Long orderId){
        return orderProductRepository.findAllByOrder_OrderId(orderId);
    }
    public void addProduct(Order order, Product product, Integer quantity){
        OrderProduct orderProduct = OrderProduct.builder().orderProductId(new OrderProductKey(order.getOrderId(), product.getProductId()))
                .order(order).product(product).quantity(quantity).build();
        order.getOrderProducts().add(orderProduct);
        orderProductRepository.save(orderProduct);
    }
    @Transactional
    public void updateProductQuantity(Long orderId, Long productId, BigDecimal quantity){
        orderProductRepository.updateQuantity(orderId,productId,quantity);
    }
    @Transactional
    public void deleteOrderProduct(Long orderId, Long productId){
        orderProductRepository.deleteOrderProductByOrder_OrderIdAndProduct_ProductId(orderId,productId);
    }
}
