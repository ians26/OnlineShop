package com.stefan.OnlineShop.controller;

import com.stefan.OnlineShop.domain.Customer;
import com.stefan.OnlineShop.domain.Order;
import com.stefan.OnlineShop.domain.OrderProduct;
import com.stefan.OnlineShop.domain.ProductQuantityRequest;
import com.stefan.OnlineShop.service.OrderProductService;
import com.stefan.OnlineShop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/api/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @PostMapping
    public ResponseEntity<Order> createOrder(){
        Order order = orderService.createOrder();
        return ResponseEntity.ok(order);
    }
    @PostMapping(path="/{orderId}/products")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Long orderId,
                                                   @RequestBody ProductQuantityRequest request){
        Order order = orderService.addProductToOrder(orderId,request);
        return ResponseEntity.ok(order);
    }

    @PostMapping(path="/{orderId}")
    public ResponseEntity<Order> completeOrder(@PathVariable Long orderId, @RequestBody Customer customer){
        Order order = orderService.completeOrder(orderId, customer);
        orderService.sendConfirmationEmail(order);
        return ResponseEntity.ok(order);
    }
    @PutMapping(path="{orderId}/products/{productId}")
    public ResponseEntity<Void> updateProductQuantity(@PathVariable Long orderId,
                                                      @PathVariable Long productId,
                                                      @RequestParam BigDecimal quantity){
        orderProductService.updateProductQuantity(orderId,productId,quantity);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(path="{orderId}/products/{productId}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long orderId, @PathVariable Long productId){
        orderProductService.deleteOrderProduct(orderId,productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path="{orderId}/products")
    public List<OrderProduct> getOrderProducts(@PathVariable Long orderId){
        return orderProductService.getOrderProducts(orderId);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<?> verifyOrder(@PathVariable Long orderId){
        Optional<Order> order = orderService.getOrderById(orderId);
        if(order.isPresent()){
            return ResponseEntity.ok(order.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



}
