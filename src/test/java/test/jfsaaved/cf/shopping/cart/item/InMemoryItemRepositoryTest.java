package test.jfsaaved.cf.shopping.cart.item;

import io.jfsaaved.cf.shopping.cart.item.InMemoryItemRepository;
import io.jfsaaved.cf.shopping.cart.item.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryItemRepositoryTest {

    @Test
    public void create() {
        InMemoryItemRepository itemRepository = new InMemoryItemRepository();

        Item createdItem = itemRepository.create(new Item("book 1", BigDecimal.valueOf(13)));

        long id = 1L;
        Item expected = new Item(id, "book 1", BigDecimal.valueOf(13));
        assertThat(createdItem).isEqualTo(expected);

        Item readEntry = itemRepository.find(createdItem.getId());
        assertThat(readEntry).isEqualTo(expected);
    }

    @Test
    public void find() {
        InMemoryItemRepository itemRepository = new InMemoryItemRepository();
        Item itemCreated =  itemRepository.create(new Item("book 1", BigDecimal.valueOf(13)));

        long id = 1L;
        Item expected = new Item(id, "book 1", BigDecimal.valueOf(13));
        Item actual = itemRepository.find(id);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void update() {
        InMemoryItemRepository itemRepository = new InMemoryItemRepository();
        Item itemCreated = itemRepository.create(new Item("book 1", BigDecimal.valueOf(13)));

        long id = 1L;
        Item expected = new Item(id, "book 2", BigDecimal.valueOf(13));
        Item actual = itemRepository.update(id, new Item(id, "book 2", BigDecimal.valueOf(13)));

        assertThat(actual).isEqualTo(expected);
        assertThat(itemRepository.find(id)).isEqualTo(expected);
    }

    @Test
    public void delete()  {

        InMemoryItemRepository itemRepository = new InMemoryItemRepository();
        Item itemCreated = itemRepository.create(new Item("book 1", BigDecimal.valueOf(13)));
        itemRepository.delete(1L);
        assertThat(itemRepository.list()).isEmpty();

    }

}
