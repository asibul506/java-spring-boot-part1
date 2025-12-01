package com.codewithfun.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public PaymentService paymentService;

    //when we have multiple construction we must use this annotation
    // otherwise there will be an error. But as long as the class
    // has only one constructor this annotation is optional
    //@Autowired
    public OrderService(@Qualifier("paypal") PaymentService paymentService) { //this qualifier let us choosing a different bean rather than primary
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

}
