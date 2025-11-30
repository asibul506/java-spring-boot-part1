package com.codewithfun.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        //SpringApplication.run(StoreApplication.class, args);

        var orderService = new OrderService(new StripePaymentService());
        orderService.placeOrder();

        orderService = new OrderService(new PaypalPaymentService());
        orderService.placeOrder();

    }

}
