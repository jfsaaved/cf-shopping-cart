package io.jfsaaved.cf.shopping.cart;

import io.jfsaaved.cf.shopping.cart.item.InMemoryItemRepository;
import io.jfsaaved.cf.shopping.cart.item.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    ItemRepository itemRepository() {
        return new InMemoryItemRepository();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
