package com.stefan.OnlineShop.repository;

import com.stefan.OnlineShop.domain.OrderProduct;
import com.stefan.OnlineShop.domain.OrderProductKey;
import com.stefan.OnlineShop.domain.Product;
import com.stefan.OnlineShop.domain.ProductAndQuantity;
import jakarta.transaction.Transactional;
import lombok.Generated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
    List<OrderProduct> findAllByOrder_OrderId(Long orderId);

    @Modifying
    @Query("UPDATE OrderProduct op SET op.quantity = :quantity" +
            " WHERE op.order.orderId = :orderId AND op.product.productId = :productId ")
    void updateQuantity(@Param("orderId") Long orderId, @Param("productId") Long productId,
                        @Param("quantity") BigDecimal quantity);
    @Modifying
    void deleteOrderProductByOrder_OrderIdAndProduct_ProductId(Long orderId, Long productId);
}
