package io.jfsaaved.cf.shopping.cart.item;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ItemHealthyIndicator implements HealthIndicator {

    private static final int MAX_TIME_ENTRIES = 5;
    private final ItemRepository repository;

    public ItemHealthyIndicator(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();

        if(repository.list().size() < MAX_TIME_ENTRIES) {
            builder.up();
        } else {
            builder.down();
        }

        return builder.build();
    }
}
