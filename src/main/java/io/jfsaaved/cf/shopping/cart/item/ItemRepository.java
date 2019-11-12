package io.jfsaaved.cf.shopping.cart.item;

import java.util.List;

public interface ItemRepository {

    Item create(Item item);
    Item find(Long id);
    List<Item> list();
    Item update(Long id, Item item);
    void delete(Long id);

}
