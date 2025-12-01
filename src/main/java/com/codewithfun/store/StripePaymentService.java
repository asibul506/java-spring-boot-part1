package com.codewithfun.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService {

    @Value("${stripe.apiUrl}")
    private String apiUrl;

    @Value("${stripe.enable}")
    private boolean stripeEnabled;

    @Value("${stripe.timeout:3000}")
    private Double timeOut;

    @Value("${stripe.supportedCurrencies}")
    private List<String> currencies;


    @Override
    public void processPayment(double amount) {
        System.out.println("STRIPE");
        System.out.println("Amount: " + amount);
        System.out.println("API URL: " + apiUrl);
        System.out.println("enabled: " + stripeEnabled);
        System.out.println("timeOut: " + timeOut);
        System.out.println("Currencies: " + currencies);

    }
}
