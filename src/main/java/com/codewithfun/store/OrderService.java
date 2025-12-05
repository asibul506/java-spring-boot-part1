package com.codewithfun.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;

//@Service
public class OrderService {
    public PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("OrderService created");
    }

    // Called by the container after the bean is constructed; use to perform any initialization
    @PostConstruct
    public void init() {
        System.out.println("OrderService PostConstruct");
    }

    // Called by the container before the bean is destroyed; use to release resources or perform cleanup
    @PreDestroy
    public void cleanup() {
        System.out.println("OrderService PreDestroy");
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

}
