package test.jfsaaved.cf.shopping.cart.item;

import io.jfsaaved.cf.shopping.cart.item.InMemoryItemRepository;
import io.jfsaaved.cf.shopping.cart.item.Item;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryItemRepositoryTest {

    @Test
    public void create() throws Exception {
        InMemoryItemRepository itemRepository = new InMemoryItemRepository();

        Item createdItem = itemRepository.create(new Item("book 1", 13L));

        long id = 1L;
        Item expected = new Item(id, "book 1", 13L);
        assertThat(createdItem).isEqualTo(expected);

        Item readEntry = itemRepository.find(createdItem.getId());
        assertThat(readEntry).isEqualTo(expected);
    }

    @Test
    public void find() throw Exception {
        InMemoryItemRepository itemRepository = new InMemoryItemRepository();
        Item itemCreated =  itemRepository.create(new Item("book 1", 13L));

        long id = 1L;
        Item expected = new Item(id, "book 1", 13L);
        Item actual = iteamRepository.find(id);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void update() throw Exception {
        InMemoryItemRepository itemRepository = new InMemoryItemRepository();
        Item itemCreated = itemRepository.create(new Item("book 1", 13L));

        long id = 1L;
        Item expected = new Item(id, "book 2", 13L);
        Item actual = itemRepository.update(id, new Item(id, "book 2", 13L));

        assertThat(actual).isEqualTo(expected);
        assertThat(itemRepository.find(id)).isEqualTo(expected);
    }

}