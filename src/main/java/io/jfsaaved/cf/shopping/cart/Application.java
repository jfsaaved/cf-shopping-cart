package io.jfsaaved.cf.shopping.cart;

import io.jfsaaved.cf.shopping.cart.item.InMemoryItemRepository;
import io.jfsaaved.cf.shopping.cart.item.Item;
import io.jfsaaved.cf.shopping.cart.item.ItemRepository;
import io.jfsaaved.cf.shopping.cart.item.JdbcItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {


    @Bean
    ItemRepository itemRepository(DataSource dataSource) {
        return new JdbcItemRepository(dataSource);
    }

    /*
    @Bean
    ItemRepository itemRepository() {
        return new InMemoryItemRepository();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
