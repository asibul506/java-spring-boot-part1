package com.codewithfun.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        /**
         * SpringApplication.run() method is used to launch a Spring application. It bootstraps the application,
         * starting the Spring context, auto-configuring beans, and performing class path scans.
         */
        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var orderService = context.getBean(OrderService.class);
        var orderService2 = context.getBean(OrderService.class);
        orderService.placeOrder();

        context.close();

    }

}
