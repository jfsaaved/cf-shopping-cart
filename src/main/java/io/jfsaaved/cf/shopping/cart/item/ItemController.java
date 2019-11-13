package io.jfsaaved.cf.shopping.cart.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        Item result = repository.create(item);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> read(@RequestParam Long id) {
        Item result = repository.find(id);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }

}
