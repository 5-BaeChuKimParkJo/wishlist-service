package com.chalnakchalnak.wishlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WishlistserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishlistserviceApplication.class, args);
    }

}
