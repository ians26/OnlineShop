package com.stefan.OnlineShop.service;

import com.stefan.OnlineShop.domain.*;
import com.stefan.OnlineShop.exception.NotFoundException;
import com.stefan.OnlineShop.repository.OrderProductRepository;
import com.stefan.OnlineShop.repository.OrderRepository;
import com.stefan.OnlineShop.repository.ProductRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderProductService orderProductService;
    private final JavaMailSender mailSender;
    public Order createOrder(){
        return orderRepository.save(Order.builder().status(OrderStatus.CREATED).build());
    }
    public Order addProductToOrder(Long orderId, ProductQuantityRequest request){
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("Order not found" +
                " with this id: "+ orderId));
        if(order.getStatus().equals(OrderStatus.CREATED)){
            order.setStatus(OrderStatus.PROCESSING);
        }
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()->new NotFoundException("Product not found with this id:" + request.getProductId()));
        orderProductService.addProduct(order,product, request.getQuantity());

        return orderRepository.save(order);
    }

    public Order completeOrder(Long orderId, Customer customer){
        Order order = orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("Order not found" +
                " with this id: "+ orderId));
        order.setCustomer(customer);
        order.setStatus(OrderStatus.COMPLETED);
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress(customer.getAddress());
        return orderRepository.save(order);
    }

    public void sendConfirmationEmail(Order order){

        Customer customer = order.getCustomer();
        MimeMessage message = mailSender.createMimeMessage();
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder_OrderId(order.getOrderId());


        // Loop through the List and generate a string for each product and its quantity
        StringBuilder productsString = new StringBuilder();
        for (OrderProduct orderProduct : orderProducts) {
            Product product = orderProduct.getProduct();
            Integer quantity = orderProduct.getQuantity();
            productsString.append("<p>" + product.getName() + " x " + quantity + "</p>");
        }


        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(customer.getEmail());
            helper.setSubject("Order Confirmation # " + order.getOrderId());
            String emailContent = "<html><body><p>Dear " + customer.getFirstName() + ",</p>" +
                    "<p>Your order #" + order.getOrderId() + " has been completed on " + order.getOrderDate() + ".</p>" +
                    "<p>You have ordered the following products: </p>" +
                    productsString.toString() +
                    "<p>Thank you for shopping with us!</p></body></html>";
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Order> getOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }
}
