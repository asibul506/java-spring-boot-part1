package com.codewithfun.store;

import org.springframework.beans.factory.annotation.Qualifier;

//@Service
public class OrderService {
    public PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("OrderService created");
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

}
