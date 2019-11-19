package io.jfsaaved.cf.shopping.cart.item;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemRepository repository;

    private final DistributionSummary itemSummary;
    private final Counter actionCounter;


    public ItemController(ItemRepository repository,  MeterRegistry meterRegistry) {
        this.repository = repository;
        this.itemSummary = meterRegistry.summary("item.summary");
        this.actionCounter = meterRegistry.counter("item.actionCounter");
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        Item result = repository.create(item);
        actionCounter.increment();
        itemSummary.record(repository.list().size());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> list() {
        List<Item> result = repository.list();
        actionCounter.increment();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> read(@PathVariable Long id) {
        Item result = repository.find(id);
        if(result != null) {
            actionCounter.increment();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        Item result = repository.update(id, item);
        actionCounter.increment();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete( @PathVariable Long id ) {
        if(repository.find(id) != null) {
            repository.delete(id);
            actionCounter.increment();
            itemSummary.record(repository.list().size());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
