package io.jfsaaved.cf.shopping.cart.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryItemRepository implements ItemRepository{

    private HashMap<Long, Item> itemEntries = new HashMap<>();
    private long currentId = 1L;

    @Override
    public Item create(Item item) {
        Long id = currentId++;

        Item newItem = new Item(
                id,
                item.getName(),
                item.getPrice()
        );

        itemEntries.put(id, newItem);
        return newItem;
    }

    @Override
    public Item find(Long id) {
        return itemEntries.get(id);
    }

    @Override
    public List<Item> list() {
        return new ArrayList<>(itemEntries.values());
    }

    @Override
    public Item update(Long id, Item item) {
        Item result = new Item(
                id,
                item.getName(),
                item.getPrice()
        );
        itemEntries.put(id, result);
        return result;
    }

    @Override
    public void delete(Long id) {
        itemEntries.remove(id);
    }


}
