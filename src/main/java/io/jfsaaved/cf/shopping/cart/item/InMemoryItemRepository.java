package io.jfsaaved.cf.shopping.cart.item;

import java.util.HashMap;
import java.util.List;

public class InMemoryItemRepository implements ItemRepository{

    private HashMap<Long, Item> itemEntries = new HashMap<>();
    private long currentId = 1L;

    @Override
    public Item create(Item item) {
        Long id = currentId++;

        Item newItem = new Item(
                item.getId(),
                item.getName(),
                item.getPrice()
        );

        itemEntries.put(id, newItem);
        return newItem;
    }

    @Override
    public Item find(Long id) {
        return null;
    }

    @Override
    public List<Item> list() {
        return null;
    }

    @Override
    public Item update(Long id, Item item) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


}
