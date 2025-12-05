package com.codewithfun.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //tells spring that this class is a source of bean definitions
public class AppConfig {

    @Value("${payment-type}")
    private String paymentType;

    @Bean //this tells spring that this method below is a bean producer
    public PaymentService stripe(){
        return new StripePaymentService();
    }

    @Bean
    public PaymentService payPal(){
        return new PaypalPaymentService();
    }

    public PaymentService paymentService() {
        if (paymentType.equals("stripe")) {
            return stripe();
        }
        return payPal();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(paymentService());
    }

}
