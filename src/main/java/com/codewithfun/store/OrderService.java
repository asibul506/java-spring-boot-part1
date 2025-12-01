package com.codewithfun.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public PaymentService paymentService;

    //when we have multiple construction we must use this annotation
    // otherwise there will be an error. But as long as the class
    // has only one constructor this annotation is optional
    //@Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

}
